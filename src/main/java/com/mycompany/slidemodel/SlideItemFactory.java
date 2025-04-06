package com.mycompany.slidemodel;

import java.io.IOException;

/**
 * <p>Abstract factory for creating slide items.</p>
 * <p>Concrete subclasses (e.g., BitmapFactory, TextItemFactory) implement the logic for creating specific types of SlideItems.</p>
 * <p>This class is part of the Factory Method design pattern, allowing dynamic creation of objects based on type.</p>
 *
 * Example use case:
 * Given an XML node with type="text" or "image", the appropriate subclass of this factory
 * will parse the data and return a corresponding SlideItem (TextItem or BitmapItem).
 */
public abstract class SlideItemFactory
{

    /**
     * Abstract method to create a SlideItem.
     *
     * @param leveltext the level of the slide item as a string (used for styling/hierarchy)
     * @param name the content of the slide item (text content or image filename)
     * @return a new SlideItem instance (e.g., TextItem or BitmapItem)
     * @throws NumberFormatException if the level text is not a valid integer
     * @throws IOException if there is an issue loading image content, etc.
     */
    public abstract SlideItem createSlideItem(String leveltext, String name) throws NumberFormatException, IOException;
}
