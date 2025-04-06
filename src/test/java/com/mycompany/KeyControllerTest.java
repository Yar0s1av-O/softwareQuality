package com.mycompany;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.event.KeyEvent;

import static org.mockito.Mockito.*;

class KeyControllerTest
{

    private Presentation mockPresentation;
    private KeyController controller;

    @BeforeEach
    void setUp()
    {
        mockPresentation = mock(Presentation.class);
        controller = new KeyController(mockPresentation);
    }

    @Test
    void testNextSlideKeysTriggerNextSlide()
    {
        int[] keys = {
                KeyEvent.VK_PAGE_DOWN, KeyEvent.VK_DOWN,
                KeyEvent.VK_ENTER, (int) '+'
        };

        for (int keyCode : keys)
        {
            KeyEvent event = new KeyEvent(new java.awt.Component() {}, 0, 0, 0, keyCode, KeyEvent.CHAR_UNDEFINED);
            controller.keyPressed(event);
        }

        // Should call nextSlide 4 times
        verify(mockPresentation, times(keys.length)).nextSlide();
    }

    @Test
    void testPrevSlideKeysTriggerPrevSlide()
    {
        int[] keys = {
                KeyEvent.VK_PAGE_UP, KeyEvent.VK_UP,
                (int) '-'
        };

        for (int keyCode : keys)
        {
            KeyEvent event = new KeyEvent(new java.awt.Component() {}, 0, 0, 0, keyCode, KeyEvent.CHAR_UNDEFINED);
            controller.keyPressed(event);
        }

        // Should call prevSlide 3 times
        verify(mockPresentation, times(keys.length)).prevSlide();
    }

    @Test
    void testUnhandledKeyDoesNothing()
    {
        KeyEvent event = new KeyEvent(new java.awt.Component() {}, 0, 0, 0, KeyEvent.VK_ESCAPE, KeyEvent.CHAR_UNDEFINED);
        controller.keyPressed(event);

        verifyNoInteractions(mockPresentation);
    }
}
