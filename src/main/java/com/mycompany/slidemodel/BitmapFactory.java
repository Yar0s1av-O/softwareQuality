package com.mycompany.slidemodel;

import java.io.IOException;

/**
 * Factory class for creating BitmapItem instances.
 * This follows the Factory Method pattern to encapsulate object creation.
 */
public class BitmapFactory extends SlideItemFactory
{

    /**
     * Creates a BitmapItem (image-based slide element) with the specified level and name.
     *
     * @param leveltext A string representing the indentation or hierarchy level of the item.
     * @param name      The filename of the image to be displayed.
     * @return A BitmapItem initialized with the given level and image name.
     * @throws NumberFormatException If the leveltext cannot be parsed into an integer.
     */
    @Override
    public SlideItem createSlideItem(String leveltext, String name) throws NumberFormatException
    {
        int level = 1;

        // If a level is specified, parse it
        if (leveltext != null) {
            level = Integer.parseInt(leveltext);
        }

        return new BitmapItem(level, name);
    }
}
