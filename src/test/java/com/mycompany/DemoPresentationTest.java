package com.mycompany;

import com.mycompany.slidemodel.Slide;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DemoPresentationTest {

    @Test
    void testLoadDemoPresentationAddsSlides() {
        DemoPresentation demo = new DemoPresentation();
        Presentation presentation = new Presentation();

        demo.loadFile(presentation, "unused");

        // Validate title
        assertEquals("Demo Presentation", presentation.getTitle(), "Title should be set by demo");

        // Validate slide count (3 slides in demo)
        assertEquals(3, presentation.getSize(), "Demo presentation should contain 3 slides");

        // Validate some text from the first slide
        Slide firstSlide = presentation.getSlide(0);
        assertNotNull(firstSlide, "First slide should not be null");
        assertEquals("JabberPoint", firstSlide.getTitle(), "First slide title mismatch");

        // Ensure there are slide items
        assertTrue(firstSlide.getSize() > 0, "First slide should have items");
    }

    @Test
    void testSaveFileThrowsException() {
        DemoPresentation demo = new DemoPresentation();
        Presentation presentation = new Presentation();

        Exception exception = assertThrows(UnsupportedOperationException.class, () -> {
            demo.saveFile(presentation, "shouldFail");
        });

        assertEquals("Save not supported for demo presentation", exception.getMessage());
    }
}
