package com.mycompany;

import com.mycompany.slidemodel.Slide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SlideManagerTest
{

    private SlideManager manager;

    @BeforeEach
    void setUp() {
        manager = new SlideManager();
    }

    @Test
    void testAddSlideAndSize() {
        Slide s1 = new Slide();
        Slide s2 = new Slide();
        manager.addSlide(s1);
        manager.addSlide(s2);

        assertEquals(2, manager.getSize());
    }

    @Test
    void testSetAndGetCurrentSlide()
    {
        Slide s1 = new Slide();
        Slide s2 = new Slide();
        manager.addSlide(s1);
        manager.addSlide(s2);

        manager.setCurrentIndex(1);
        assertEquals(s2, manager.getCurrentSlide());
    }

    @Test
    void testSlideNavigation()
    {
        manager.addSlide(new Slide());
        manager.addSlide(new Slide());

        manager.setCurrentIndex(0);
        assertTrue(manager.hasNext());
        assertFalse(manager.hasPrevious());

        manager.nextSlide();
        assertFalse(manager.hasNext());
        assertTrue(manager.hasPrevious());

        manager.prevSlide();
        assertEquals(0, manager.getCurrentIndex());
    }

    @Test
    void testClearSlides()
    {
        manager.addSlide(new Slide());
        manager.setCurrentIndex(0);
        manager.clear();

        assertEquals(0, manager.getSize());
        assertNull(manager.getCurrentSlide());
    }
}
