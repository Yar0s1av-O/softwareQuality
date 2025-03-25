package com.mycompany.slidemodel;

import java.io.IOException;

public abstract class SlideItemFactory
{

    public abstract SlideItem createSlideItem(String leveltext, String name) throws NumberFormatException, IOException;
}