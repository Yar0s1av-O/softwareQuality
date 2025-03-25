package com.mycompany.slidemodel;

import java.io.IOException;

public class SlideItemFactory {

    protected static final String UNKNOWNTYPE = "Unknown Element type";

    public SlideItem createSlideItem(String leveltext, String type, String name) throws NumberFormatException, IOException {
        int level = 1;
        if (leveltext != null) level = Integer.parseInt(leveltext);
        switch (type) {
            case "text":
                return new TextItem(level, name);
            case "image":
                return new BitmapItem(level, name);
            default:
                throw new IOException(UNKNOWNTYPE + " :type");
        }
    }
}