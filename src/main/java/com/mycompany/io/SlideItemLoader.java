package com.mycompany.io;

import com.mycompany.slidemodel.*;

import java.io.IOException;

/**
 * Utility class responsible for loading SlideItems dynamically
 * based on their type (e.g., text or image).
 */
public class SlideItemLoader {

    /**
     * Loads a SlideItem by determining its type and using the corresponding factory.
     *
     * @param type      The type of the slide item ("text" or "image")
     * @param leveltext The level of indentation/styling (usually a number as string)
     * @param content   The actual content of the slide item (text or image name)
     * @return A new SlideItem instance based on the provided parameters
     * @throws IOException If the type is unrecognized or parsing fails
     */
    public static SlideItem loadSlideItem(String type, String leveltext, String content) throws IOException {
        SlideItemFactory factory;

        // Determine which factory to use based on the slide item type
        if ("text".equalsIgnoreCase(type)) {
            factory = new TextItemFactory();
        } else if ("image".equalsIgnoreCase(type)) {
            factory = new BitmapFactory();
        } else {
            // Throw an error if the item type is unknown
            throw new IOException("Unknown Element type: " + type);
        }

        // Use the selected factory to create and return the SlideItem
        return factory.createSlideItem(leveltext, content);
    }
}
