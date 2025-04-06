package com.mycompany;

import java.awt.Color;
import java.awt.Font;

/**
 * Style encapsulates visual formatting for slide items, such as indentation,
 * color, font, font size, and leading (line spacing).
 *
 * There is a fixed relationship between slide item levels and styles: a style is
 * selected based on the item's level using the getStyle method.
 */
public class Style {
    private static Style[] styles;  // Array holding predefined styles for different levels
    private static final String FONTNAME = "Helvetica";  // Default font family

    public int indent;     // Horizontal indent for the slide item
    public Color color;    // Text color
    public Font font;      // Font object for rendering
    public int fontSize;   // Font size in points
    public int leading;    // Line spacing

    /**
     * Initializes default styles for levels 0 to 4.
     * These are hardcoded and linked to slide item levels.
     */
    public static void createStyles() {
        styles = new Style[5];
        styles[0] = new Style(0, Color.red, 48, 20);
        styles[1] = new Style(20, Color.blue, 40, 10);
        styles[2] = new Style(50, Color.black, 36, 10);
        styles[3] = new Style(70, Color.black, 30, 10);
        styles[4] = new Style(90, Color.black, 24, 10);
    }

    /**
     * Retrieves the appropriate style for a given item level.
     * If the level exceeds the predefined range, the last style is returned.
     *
     * @param level The item level (e.g., header level).
     * @return Corresponding Style object.
     */
    public static Style getStyle(int level) {
        if (level >= styles.length) {
            level = styles.length - 1;  // Fallback to the last defined style
        }
        return styles[level];
    }

    /**
     * Constructs a style with specified parameters.
     *
     * @param indent Horizontal indent
     * @param color  Text color
     * @param points Font size in points
     * @param leading Line spacing
     */
    public Style(int indent, Color color, int points, int leading) {
        this.indent = indent;
        this.color = color;
        this.font = new Font(FONTNAME, Font.BOLD, fontSize = points);
        this.leading = leading;
    }

    @Override
    public String toString() {
        return "[" + this.indent + "," + this.color + "; " + this.fontSize + " on " + this.leading + "]";
    }

    /**
     * Returns the scaled font based on the specified multiplier.
     *
     * @param scale A multiplier for font size (e.g., zoom level).
     * @return A derived font scaled accordingly.
     */
    public Font getFont(float scale) {
        return this.font.deriveFont(this.fontSize * scale);
    }
}
