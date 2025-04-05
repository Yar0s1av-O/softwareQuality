package com.mycompany.commands;

import com.mycompany.Presentation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class NextSlideCommandTest {

    private Presentation mockPresentation;
    private NextSlideCommand command;

    @BeforeEach
    void setUp() {
        mockPresentation = mock(Presentation.class);
        command = new NextSlideCommand(mockPresentation);
    }

    @Test
    void testExecute_callsNextSlide() {
        // Act
        command.execute();

        // Assert
        verify(mockPresentation, times(1)).nextSlide();
    }
}
