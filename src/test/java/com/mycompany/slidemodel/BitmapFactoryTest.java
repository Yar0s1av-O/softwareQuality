package com.mycompany.slidemodel;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BitmapFactoryTest {

    private final BitmapFactory factory = new BitmapFactory();

    @Test
    void testCreateSlideItemWithValidLevel()
    {
        SlideItem item = factory.createSlideItem("2", "serclogo_fc.jpg");

        assertTrue(item instanceof BitmapItem);
        assertEquals(2, item.getLevel());
        assertEquals("serclogo_fc.jpg", ((BitmapItem) item).getName());
    }

    @Test
    void testCreateSlideItemWithNullLevel()
    {
        SlideItem item = factory.createSlideItem(null, "fallback.jpg");

        assertTrue(item instanceof BitmapItem);
        assertEquals(1, item.getLevel());  // default level
        assertEquals("fallback.jpg", ((BitmapItem) item).getName());
    }

    @Test
    void testCreateSlideItemWithInvalidLevelText()
    {
        assertThrows(NumberFormatException.class, () -> {
            factory.createSlideItem("abc", "invalid.jpg");
        });
    }
}
