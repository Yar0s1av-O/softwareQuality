package com.mycompany.slidemodel;

import com.mycompany.Style;

import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.font.TextLayout;
import java.awt.font.TextAttribute;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.ImageObserver;
import java.text.AttributedString;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

/**
 * <p>A TextItem represents a text element on a slide.</p>
 * <p>It has drawing functionality and calculates layout/position based on styling and scale.</p>
 */
public class TextItem extends SlideItem
{

    private String text;
    private static final String EMPTYTEXT = "No Text Given";

    /**
     * Constructs a TextItem with the specified level and text.
     *
     * @param level the slide item level (used to determine styling)
     * @param string the actual text content
     */
    public TextItem(int level, String string)
    {
        super(level);
        text = string;
    }

    /**
     * Default constructor that initializes a TextItem with empty fallback text.
     */
    public TextItem()
    {
        this(0, EMPTYTEXT);
    }

    /**
     * Returns the text content, or an empty string if null.
     */
    public String getText()
    {
        return text == null ? "" : text;
    }

    /**
     * Builds an AttributedString using the current style and scale.
     * This is used by text rendering components for consistent styling.
     */
    public AttributedString getAttributedString(Style style, float scale)
    {
        AttributedString attrStr = new AttributedString(getText());
        attrStr.addAttribute(TextAttribute.FONT, style.getFont(scale), 0, text.length());
        return attrStr;
    }

    /**
     * Calculates the bounding box needed to render the text, based on font style and scaling.
     */
    @Override
    public Rectangle getBoundingBox(Graphics g, ImageObserver observer, float scale, Style myStyle)
    {
        List<TextLayout> layouts = getLayouts(g, myStyle, scale);
        int xsize = 0, ysize = (int) (myStyle.leading * scale);
        Iterator<TextLayout> iterator = layouts.iterator();

        while (iterator.hasNext())
        {
            TextLayout layout = iterator.next();
            Rectangle2D bounds = layout.getBounds();

            if (bounds.getWidth() > xsize)
            {
                xsize = (int) bounds.getWidth();
            }
            if (bounds.getHeight() > 0)
            {
                ysize += bounds.getHeight();
            }

            ysize += layout.getLeading() + layout.getDescent();
        }

        return new Rectangle((int) (myStyle.indent * scale), 0, xsize, ysize);
    }

    /**
     * Draws the text onto the slide using styling and scale.
     */
    @Override
    public void draw(int x, int y, float scale, Graphics g,
                     Style myStyle, ImageObserver o)
    {
        if (text == null || text.isEmpty())
        {
            return;
        }

        List<TextLayout> layouts = getLayouts(g, myStyle, scale);
        Point pen = new Point(x + (int) (myStyle.indent * scale), y + (int) (myStyle.leading * scale));
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(myStyle.color);
        Iterator<TextLayout> it = layouts.iterator();

        while (it.hasNext())
        {
            TextLayout layout = it.next();

            pen.y += layout.getAscent();
            layout.draw(g2d, pen.x, pen.y);
            pen.y += layout.getDescent();
        }
    }

    /**
     * Breaks the text into multiple lines that fit within the defined slide width,
     * applying the style and scale. Used in rendering and layout calculation.
     */
    private List<TextLayout> getLayouts(Graphics g, Style s, float scale)
    {
        List<TextLayout> layouts = new ArrayList<>();
        AttributedString attrStr = getAttributedString(s, scale);
        Graphics2D g2d = (Graphics2D) g;
        FontRenderContext frc = g2d.getFontRenderContext();
        LineBreakMeasurer measurer = new LineBreakMeasurer(attrStr.getIterator(), frc);
        float wrappingWidth = (Slide.WIDTH - s.indent) * scale;

        while (measurer.getPosition() < getText().length())
        {
            TextLayout layout = measurer.nextLayout(wrappingWidth);
            layouts.add(layout);
        }

        return layouts;
    }

    /**
     * Returns a string representation for debugging purposes.
     */
    @Override
    public String toString() {
        return "TextItem[" + getLevel() + "," + getText() + "]";
    }
}
