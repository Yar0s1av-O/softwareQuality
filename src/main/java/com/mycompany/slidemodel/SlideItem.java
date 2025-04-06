package com.mycompany.slidemodel;

import com.mycompany.Style;

import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.image.ImageObserver;

/**
 * <p>The abstract class for an item on a slide.</p>
 * <p>All SlideItems must be able to draw themselves and report their size (bounding box).</p>
 * <p>Common types of SlideItems include text and image items.</p>
 *
 * This class uses the Template Method design pattern, where concrete subclasses (e.g., TextItem, BitmapItem)
 * provide specific implementations for drawing and sizing.
 *
 * @author Ian F. Darwin, ian@darwinsys.com,
 * Gert Florijn, Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */
public abstract class SlideItem {

    // Level indicates hierarchy/indentation on the slide (used for styling)
    private final int level;

    /**
     * Constructor specifying the hierarchy level of the item.
     * @param lev the indentation or nesting level
     */
    public SlideItem(int lev) {
        level = lev;
    }

    /**
     * Default constructor with level 0 (highest level).
     */
    public SlideItem() {
        this(0);
    }

    /**
     * Returns the hierarchy level of the item.
     */
    public int getLevel() {
        return level;
    }

    /**
     * Each SlideItem must be able to calculate its bounding box (dimensions) for layout purposes.
     *
     * @param g        the graphics context
     * @param observer the image observer
     * @param scale    the current scaling factor
     * @param style    the visual style for the item
     * @return a Rectangle representing the bounding box of the item
     */
    public abstract Rectangle getBoundingBox(Graphics g, ImageObserver observer, float scale, Style style);

    /**
     * Each SlideItem must be able to draw itself.
     *
     * @param x         the horizontal starting position
     * @param y         the vertical starting position
     * @param scale     the current scaling factor
     * @param g         the graphics context
     * @param style     the visual style to use
     * @param observer  the image observer (used for image rendering)
     */
    public abstract void draw(int x, int y, float scale, Graphics g, Style style, ImageObserver observer);
}
