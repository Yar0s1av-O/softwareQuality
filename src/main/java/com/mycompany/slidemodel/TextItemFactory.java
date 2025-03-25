package com.mycompany.slidemodel;

public class TextItemFactory extends SlideItemFactory
{
    @Override
    public SlideItem createSlideItem(String leveltext, String name) throws NumberFormatException
    {
        int level = 1;
        if (leveltext != null)
        {
            level = Integer.parseInt(leveltext);
        }
        return new TextItem(level, name);
    }
}
