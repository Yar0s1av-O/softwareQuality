package com.mycompany.slidemodel;

import com.mycompany.Style;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import static org.junit.jupiter.api.Assertions.*;

class BitmapItemTest {

    @Test
    void testConstructorWithValidName()
    {
        BitmapItem item = new BitmapItem(1, "serclogo_fc.jpg");
        assertEquals("serclogo_fc.jpg", item.getName());
        assertEquals(1, item.getLevel());
    }

    @Test
    void testConstructorWithInvalidName()
    {
        BitmapItem item = new BitmapItem(1, "non_existing_image.jpg");
        assertEquals("non_existing_image.jpg", item.getName());
        assertEquals(1, item.getLevel());
    }

    @Test
    void testConstructorWithEmptyName()
    {
        BitmapItem item = new BitmapItem(1, "");
        assertEquals("", item.getName());
        assertEquals(1, item.getLevel());
    }

    @Test
    void testToString()
    {
        BitmapItem item = new BitmapItem(2, "serclogo_fc.jpg");
        assertTrue(item.toString().contains("BitmapItem[2,serclogo_fc.jpg]"));
    }

    @Test
    void testGetBoundingBoxWithNullImage()
    {
        BitmapItem item = new BitmapItem(); // this creates with null image
        Style style = new Style(20, Color.BLACK, 24, 20);
        Rectangle box = item.getBoundingBox(null, null, 1.0f, style);
        assertEquals(new Rectangle(0, 0, 0, 0), box);
    }

    @Test
    void testDrawWithNullImage()
    {
        BitmapItem item = new BitmapItem(); // creates with null image
        Graphics g = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB).getGraphics();
        Style style = new Style(20, Color.BLACK, 24, 20);
        ImageObserver observer = (img, infoflags, x, y, width, height) -> false;
        // This shouldn't throw any exceptions
        item.draw(0, 0, 1.0f, g, style, observer);
    }
}
