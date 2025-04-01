package com.mycompany.io;

import com.mycompany.slidemodel.*;

import java.io.IOException;

public class SlideItemLoader
{

    public static SlideItem loadSlideItem(String type, String leveltext, String content) throws IOException
    {
        SlideItemFactory factory;

        if ("text".equalsIgnoreCase(type))
        {
            factory = new TextItemFactory();
        }
        else if ("image".equalsIgnoreCase(type))
        {
            factory = new BitmapFactory();
        }
        else
        {
            throw new IOException("Unknown Element type: " + type);
        }

        return factory.createSlideItem(leveltext, content);
    }
}
