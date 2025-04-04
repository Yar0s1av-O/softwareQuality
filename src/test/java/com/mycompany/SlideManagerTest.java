package com.mycompany;

import com.mycompany.slidemodel.Slide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SlideManagerTest {

    private SlideManager manager;

    @BeforeEach
    void setUp() {
        manager = new SlideManager();
    }

    @Test
    void testAddSlideIncreasesSize() {
        Slide slide = new Slide();
        manager.addSlide(slide);
        assertEquals(1, manager.getSize());
    }

    @Test
    void testClearSlidesEmptiesManager() {
        manager.addSlide(new Slide());
        manager.clear();
        assertEquals(0, manager.getSize());
    }

    @Test
    void testNavigationNextAndPrevious() {
        manager.addSlide(new Slide());
        manager.addSlide(new Slide());
        manager.setCurrentIndex(0);

        assertTrue(manager.hasNext());
        manager.nextSlide();
        assertEquals(1, manager.getCurrentIndex());

        assertTrue(manager.hasPrevious());
        manager.prevSlide();
        assertEquals(0, manager.getCurrentIndex());
    }
}
