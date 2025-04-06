package com.mycompany.commands;

import com.mycompany.Presentation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

public class NewPresentationCommandTest
{
    @Test
    void newPresentationCommand_doesNotThrow(){
        Presentation presentation = mock(Presentation.class);
        Assertions.assertDoesNotThrow(()->{Command command = new NewPresentationCommand(presentation);});
    }

    @Test
    void execute_doesNotThrow(){
        Presentation presentation = mock(Presentation.class);
        Command command = new NewPresentationCommand(presentation);

        Assertions.assertDoesNotThrow(()->command.execute());
    }
}
