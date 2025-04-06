package com.mycompany;

import com.mycompany.slidemodel.Slide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class SlideViewerComponentTest {

    private Presentation mockPresentation;
    private JFrame mockFrame;
    private SlideViewerComponent component;

    @BeforeEach
    void setUp() {
        mockPresentation = mock(Presentation.class);
        mockFrame = mock(JFrame.class);
        component = new SlideViewerComponent(mockPresentation, mockFrame);
    }

    @Test
    void testUpdateWithValidSlide() {
        Slide mockSlide = mock(Slide.class);
        when(mockPresentation.getTitle()).thenReturn("Test Presentation");

        component.update(mockPresentation, mockSlide);

        // Should update title
        verify(mockFrame).setTitle("Test Presentation");
    }

    @Test
    void testUpdateWithNullSlideDoesNotCrash() {
        // Slide is null, should just repaint without exception
        component.update(mockPresentation, null);
        // You can’t verify UI output directly here, but you ensure no exceptions are thrown
    }

    @Test
    void testPaintComponentWithNoSlideDoesNotDraw() {
        Graphics mockGraphics = mock(Graphics.class);
        when(mockPresentation.getSlideNumber()).thenReturn(-1);

        // This just ensures no exceptions and skips drawing when no slide is set
        component.paintComponent(mockGraphics);
    }

    @Test
    void testPaintComponentWithSlideDraws() {
        Slide mockSlide = mock(Slide.class);
        Graphics mockGraphics = mock(Graphics.class);
        when(mockPresentation.getSlideNumber()).thenReturn(0);
        when(mockPresentation.getSize()).thenReturn(5);

        component.update(mockPresentation, mockSlide);
        component.paintComponent(mockGraphics);

        // Verify drawing happens
        verify(mockGraphics).drawString(contains("Slide 1 of"), anyInt(), anyInt());
        verify(mockSlide).draw(any(Graphics.class), any(Rectangle.class), eq(component));
    }
}
