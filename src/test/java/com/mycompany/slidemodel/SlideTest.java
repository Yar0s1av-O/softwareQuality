package com.mycompany.slidemodel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

class SlideTest
{

    private Slide slide;

    @BeforeEach
    void setUp()
    {
        slide = new Slide();
    }

    @Test
    void testSetAndGetTitle()
    {
        slide.setTitle("Test Slide");
        assertEquals("Test Slide", slide.getTitle(), "Slide title should be correctly set and retrieved");
    }

    @Test
    void testAppendAndGetSlideItems()
    {
        TextItem textItem = new TextItem(1, "Sample text");
        slide.append(textItem);

        Vector<SlideItem> items = slide.getSlideItems();
        assertEquals(1, items.size(), "Slide should contain 1 item after append");
        assertSame(textItem, items.get(0), "Appended item should match the inserted one");
    }

    @Test
    void testAppendNullItemIsIgnoredOrHandled()
    {
        slide.append(null);
        assertEquals(0, slide.getSlideItems().size(), "Appending null should not add items");
    }

    @Test
    void testMultipleItems()
    {
        SlideItem item1 = new TextItem(1, "Item 1");
        SlideItem item2 = new TextItem(2, "Item 2");

        slide.append(item1);
        slide.append(item2);

        assertEquals(2, slide.getSlideItems().size(), "Should contain 2 items");
        assertEquals("Item 2", ((TextItem) slide.getSlideItems().get(1)).getText());
    }

    @Test
    void testAppend_shouldHandleNonNullAndNullItems()
    {
        Slide slide = new Slide();

        // This line tests the `if (anItem != null)` = false case
        slide.append((SlideItem) null);
        assertEquals(0, slide.getSize(), "Null SlideItem should not be added");

        // This line tests the `if (anItem != null)` = true case
        slide.append(new TextItem(1, "Test"));
        assertEquals(1, slide.getSize(), "Valid SlideItem should be added");
    }

}
