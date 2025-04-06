package com.mycompany.commands;

import com.mycompany.Presentation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GoToSlideCommandTest
{

    @Test
    void GoToSlideCommand_noExceptions(){
        Presentation mockPresentation = mock(Presentation.class);
        Assertions.assertDoesNotThrow(()-> {new GoToSlideCommand(mockPresentation, 1);});
    }

    @Test
    void execute_setSlideTwo_slideTwo() {
        Presentation mockPresentation = mock(Presentation.class);
        GoToSlideCommand command = new GoToSlideCommand(mockPresentation, 2);
        command.execute();

        verify(mockPresentation, times(1)).setSlideNumber(2);
    }

    @Test
    void execute_outside_noException() {
        Presentation mockPresentation = mock(Presentation.class);
        GoToSlideCommand command = new GoToSlideCommand(mockPresentation, -12);

        Assertions.assertDoesNotThrow(()-> {command.execute();});

    }


}
