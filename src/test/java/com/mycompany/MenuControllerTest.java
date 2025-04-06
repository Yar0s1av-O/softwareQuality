package com.mycompany;

import com.mycompany.commands.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.Frame;
import java.awt.MenuBar;

import static org.mockito.Mockito.*;

class MenuControllerTest {

    private Frame mockFrame;
    private Presentation mockPresentation;
    private CommandInvoker mockInvoker;
    private MenuController menuController;

    @BeforeEach
    void setUp()
    {
        mockFrame = mock(Frame.class);
        mockPresentation = mock(Presentation.class);
        mockInvoker = mock(CommandInvoker.class);
        menuController = new MenuController(mockFrame, mockPresentation, mockInvoker);
    }

    @Test
    void testCommandsAreRegistered()
    {
        // Verify that all the key commands were registered
        verify(mockInvoker).register(eq("Open"), any(OpenPresentationCommand.class));
        verify(mockInvoker).register(eq("New"), any(NewPresentationCommand.class));
        verify(mockInvoker).register(eq("Save"), any(SavePresentationCommand.class));
        verify(mockInvoker).register(eq("Exit"), any(ExitCommand.class));
        verify(mockInvoker).register(eq("Next"), any(NextSlideCommand.class));
        verify(mockInvoker).register(eq("Prev"), any(PrevSlideCommand.class));
        verify(mockInvoker).register(eq("About"), any(ShowAboutBoxCommand.class));
    }

    @Test
    void testMenuControllerExtendsMenuBar()
    {
        // Ensure that the controller can be added as a menu bar to a frame
        assert(menuController instanceof MenuBar);
    }
}
