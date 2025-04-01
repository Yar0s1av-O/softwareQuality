package com.mycompany;

import java.awt.Color;
import java.awt.Font;

/**
 * <p>Style is for Indent, Color, Font and Leading.</p>
 * <p>Direct relation between style-number and item-level:
 * in Slide style if fetched for an item
 * with style-number as item-level.</p>
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class Style
{
    private static Style[] styles;
    private static final String FONTNAME = "Helvetica";
    public int indent;
    public Color color;
    public Font font;
    public int fontSize;
    public int leading;

    public static void createStyles()
    {
        styles = new Style[5];

        styles[0] = new Style(0, Color.red, 48, 20);
        styles[1] = new Style(20, Color.blue, 40, 10);
        styles[2] = new Style(50, Color.black, 36, 10);
        styles[3] = new Style(70, Color.black, 30, 10);
        styles[4] = new Style(90, Color.black, 24, 10);
    }

    public static Style getStyle(int level)
    {
        if (level >= styles.length)
        {
            level = styles.length - 1;
        }

        return styles[level];
    }

    public Style(int indent, Color color, int points, int leading)
    {
        this.indent = indent;
        this.color = color;
        this.font = new Font(this.FONTNAME, Font.BOLD, fontSize = points);
        this.leading = leading;
    }

    public String toString()
    {
        return "[" + this.indent + "," + this.color + "; " + this.fontSize + " on " + this.leading + "]";
    }

    public Font getFont(float scale)
    {
        return this.font.deriveFont(this.fontSize * scale);
    }
}
