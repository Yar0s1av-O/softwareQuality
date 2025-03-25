package com.mycompany.slidemodel;

import java.io.IOException;

public class BitmapFactory extends SlideItemFactory
{

        @Override
        public SlideItem createSlideItem(String leveltext, String name) throws NumberFormatException {
            int level = 1;
            if (leveltext != null) {
                level = Integer.parseInt(leveltext);
            }
            return new BitmapItem(level, name);
        }
    }

