package com.mycompany.slidemodel;

import com.mycompany.Presentation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PresentationTest
{

    private Presentation presentation;

    @BeforeEach
    void setUp()
    {
        presentation = new Presentation();
    }

    @Test
    void testSetAndGetTitle()
    {
        presentation.setTitle("My Presentation");
        assertEquals("My Presentation", presentation.getTitle(), "Title should be correctly set and retrieved");
    }

    @Test
    void testAppendSlideAndGetSize()
    {
        Slide slide1 = new Slide();
        Slide slide2 = new Slide();

        presentation.append(slide1);
        presentation.append(slide2);

        assertEquals(2, presentation.getSize(), "Presentation should have 2 slides");
    }

    @Test
    void testGetSlideByIndex()
    {
        Slide slide = new Slide();
        slide.setTitle("Intro Slide");

        presentation.append(slide);
        Slide result = presentation.getSlide(0);

        assertNotNull(result);
        assertEquals("Intro Slide", result.getTitle(), "Retrieved slide should match the added one");
    }

    @Test
    void testClearPresentation()
    {
        presentation.append(new Slide());
        presentation.append(new Slide());

        presentation.clear();

        assertEquals(0, presentation.getSize(), "Cleared presentation should have 0 slides");
        assertNull(presentation.getCurrentSlide(), "Current slide should be null after clearing");
    }

    @Test
    void testCurrentSlideTracking()
    {
        Slide slide1 = new Slide();
        Slide slide2 = new Slide();

        presentation.append(slide1);
        presentation.append(slide2);

        presentation.setSlideNumber(0);
        assertSame(slide1, presentation.getCurrentSlide(), "First slide should be current after setting index to 0");

        presentation.setSlideNumber(1);
        assertSame(slide2, presentation.getCurrentSlide(), "Second slide should be current after setting index to 1");
    }

    @Test
    void testNextAndPreviousSlideNavigation()
    {
        Slide slide1 = new Slide();
        Slide slide2 = new Slide();
        Slide slide3 = new Slide();

        presentation.append(slide1);
        presentation.append(slide2);
        presentation.append(slide3);

        presentation.setSlideNumber(0);
        presentation.nextSlide();
        assertSame(slide2, presentation.getCurrentSlide(), "After nextSlide, should be on slide2");

        presentation.prevSlide();
        assertSame(slide1, presentation.getCurrentSlide(), "After prevSlide, should be back to slide1");
    }

    @Test
    void testNavigationLimits()
    {
        Slide slide1 = new Slide();
        presentation.append(slide1);

        presentation.setSlideNumber(0);
        presentation.prevSlide(); // Should not crash or go below 0
        assertSame(slide1, presentation.getCurrentSlide(), "prevSlide at index 0 should stay at slide1");

        presentation.nextSlide(); // No next slide, should stay the same
        assertSame(slide1, presentation.getCurrentSlide(), "nextSlide with no additional slides should stay at slide1");
    }
}
