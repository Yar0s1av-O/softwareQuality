package com.mycompany;

import com.mycompany.commands.CommandInvoker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assertions.*;

class SlideViewerFrameTest {

    private SlideViewerFrame frame;
    private Presentation presentation;

    @BeforeEach
    void setup() {
        //  Skip this test in headless environments like GitHub Actions
        assumeTrue(!GraphicsEnvironment.isHeadless(), "Skipping GUI tests in headless environment");

        Style.createStyles();
        presentation = new Presentation();
        frame = new SlideViewerFrame("Test Frame", presentation);
    }

    @Test
    void testFrameTitle() {
        assertEquals("Jabberpoint 1.6 - OU", frame.getTitle());
    }

    @Test
    void testFrameSize() {
        Dimension expectedSize = new Dimension(SlideViewerFrame.WIDTH, SlideViewerFrame.HEIGHT);
        assertEquals(expectedSize, frame.getSize());
    }

    @Test
    void testFrameMenuBarIsSet() {
        MenuBar menuBar = frame.getMenuBar();
        assertNotNull(menuBar, "Menu bar should be set");
    }
}
