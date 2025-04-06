package com.mycompany;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class SlideViewerFrameTest
{

    private SlideViewerFrame frame;
    private Presentation presentation;

    @BeforeEach
    void setup()
    {
        // Ensure headless mode for test environments (e.g., GitHub Actions)
        System.setProperty("java.awt.headless", "true");

        presentation = new Presentation();
        frame = new SlideViewerFrame("Test Title", presentation);
    }

    @Test
    void testFrameTitleAndVisibility()
    {
        assertEquals("Jabberpoint 1.6 - OU", frame.getTitle());
        assertTrue(frame.isVisible());
    }

    @Test
    void testMenuBarExists()
    {
        assertNotNull(frame.getMenuBar());
        assertTrue(frame.getMenuBar() instanceof MenuController);
    }

    @Test
    void testSlideViewerComponentAttached()
    {
        Component[] components = frame.getContentPane().getComponents();
        assertTrue(components.length > 0);
        assertTrue(components[0] instanceof SlideViewerComponent);
    }
}
