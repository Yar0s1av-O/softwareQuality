package com.mycompany;

import com.mycompany.slidemodel.Slide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SlideNavigatorTest
{

    private List<Slide> slides;
    private SlideNavigator navigator;

    @BeforeEach
    void setUp()
    {
        slides = new ArrayList<>();
        slides.add(new Slide());
        slides.add(new Slide());
        navigator = new SlideNavigator(slides);
    }

    @Test
    void testInitialCurrentIndex()
    {
        assertEquals(-1, navigator.getCurrentIndex(), "Initial index should be -1");
        assertNull(navigator.getCurrentSlide(), "No current slide at initial state");
    }

    @Test
    void testSetValidCurrentIndex()
    {
        navigator.setCurrentIndex(0);
        assertEquals(0, navigator.getCurrentIndex());
        assertNotNull(navigator.getCurrentSlide());
    }

    @Test
    void testSetInvalidCurrentIndex()
    {
        navigator.setCurrentIndex(5);
        assertEquals(-1, navigator.getCurrentIndex(), "Index should remain unchanged when out of bounds");
    }

    @Test
    void testNextAndPrevious()
    {
        navigator.setCurrentIndex(0);
        assertTrue(navigator.hasNext());
        assertFalse(navigator.hasPrevious());

        navigator.next();
        assertEquals(1, navigator.getCurrentIndex());
        assertTrue(navigator.hasPrevious());
        assertFalse(navigator.hasNext());

        navigator.previous();
        assertEquals(0, navigator.getCurrentIndex());
    }
}