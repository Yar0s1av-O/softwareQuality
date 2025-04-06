package com.mycompany.slidemodel;

import com.mycompany.Style;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.util.Vector;

/**
 * <p>A slide. This class has a drawing functionality.</p>
 * <p>Each slide has a title and a list of SlideItems (such as text or images).</p>
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */
public class Slide {
    // Constant dimensions for standard slide size
    public final static int WIDTH = 1200;
    public final static int HEIGHT = 800;

    protected String title; // Title of the slide
    protected Vector<SlideItem> items; // Collection of slide items (text/images)

    /**
     * Constructor initializes the item list.
     */
    public Slide() {
        items = new Vector<>();
    }

    /**
     * Adds a SlideItem to the slide.
     * Ignores null values to ensure data consistency.
     */
    public void append(SlideItem anItem) {
        if (anItem != null) {
            items.addElement(anItem);
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String newTitle) {
        title = newTitle;
    }

    /**
     * Convenience method to create and add a TextItem.
     *
     * @param level   the indentation level
     * @param message the text content
     */
    public void append(int level, String message) {
        append(new TextItem(level, message));
    }

    /**
     * Gets a specific SlideItem by index.
     */
    public SlideItem getSlideItem(int number) {
        return items.elementAt(number);
    }

    /**
     * Returns all slide items.
     */
    public Vector<SlideItem> getSlideItems() {
        return items;
    }

    /**
     * @return the number of items in the slide
     */
    public int getSize() {
        return items.size();
    }

    /**
     * Renders the slide contents (title + all slide items) to the given graphics context.
     */
    public void draw(Graphics g, Rectangle area, ImageObserver view) {
        float scale = getScale(area); // Calculate scale based on area size
        int y = area.y;

        // Draw the title as a special TextItem with level 0
        SlideItem slideItem = new TextItem(0, getTitle());
        Style style = Style.getStyle(slideItem.getLevel());
        slideItem.draw(area.x, y, scale, g, style, view);
        y += slideItem.getBoundingBox(g, view, scale, style).height;

        // Draw each item and adjust vertical position
        for (int number = 0; number < getSize(); number++) {
            slideItem = getSlideItems().elementAt(number);
            style = Style.getStyle(slideItem.getLevel());
            slideItem.draw(area.x, y, scale, g, style, view);
            y += slideItem.getBoundingBox(g, view, scale, style).height;
        }
    }

    /**
     * Determines the scaling factor to fit the slide inside the specified area.
     */
    private float getScale(Rectangle area) {
        return Math.min(((float) area.width) / ((float) WIDTH),
                ((float) area.height) / ((float) HEIGHT));
    }
}
