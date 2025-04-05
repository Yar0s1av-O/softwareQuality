package com.mycompany.io;

import com.mycompany.slidemodel.BitmapItem;
import com.mycompany.slidemodel.TextItem;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class SlideItemLoaderTest
{

    @Test
    void loadSlideItem_textLowercase_textItemReturned() throws IOException
    {
        String type = "text";
        String leveltext = "1";
        String content = "content";


        Assertions.assertInstanceOf(TextItem.class, SlideItemLoader.loadSlideItem(type, leveltext, content));
    }

    @Test
    void loadSlideItem_textMixedCase_textItemReturned() throws IOException
    {
        String type = "tExT";
        String leveltext = "1";
        String content = "content";


        Assertions.assertInstanceOf(TextItem.class, SlideItemLoader.loadSlideItem(type, leveltext, content));
    }

    @Test
    void loadSlideItem_imageLowercase_imageItemReturned() throws IOException
    {
        String type = "image";
        String leveltext = "2";
        String content = "content";


        Assertions.assertInstanceOf(BitmapItem.class, SlideItemLoader.loadSlideItem(type, leveltext, content));
    }

    @Test
    void loadSlideItem_imageMixedcase_imageItemReturned() throws IOException
    {
        String type = "iMAGe";
        String leveltext = "2";
        String content = "content";


        Assertions.assertInstanceOf(BitmapItem.class, SlideItemLoader.loadSlideItem(type, leveltext, content));
    }

    @Test
    void loadSlideItem_wrontType_IOException() throws IOException
    {
        String type = "non existent";
        String leveltext = "2";
        String content = "content";


        Assertions.assertThrows(IOException.class, ()-> {SlideItemLoader.loadSlideItem(type, leveltext, content);});
    }

}
