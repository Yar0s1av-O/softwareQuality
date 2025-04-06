package com.mycompany.commands;

import com.mycompany.Presentation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class CommandInvokerTest
{

    @Test
    void register()
    {
        Command command = new ExitCommand();

        CommandInvoker commandInvoker = new CommandInvoker();
        commandInvoker.register("exit", command);

        assertEquals(1, commandInvoker.getCommandMap().size());
    }


    @Test
    void execute_nonexitentCommand_noException()
    {
        CommandInvoker commandInvoker = new CommandInvoker();

        assertDoesNotThrow(()->{commandInvoker.execute("show");});
    }

    @Test
    void execute_command_doesNotThrow(){
        Presentation presentation = mock(Presentation.class);
        Command goToCommand = new GoToSlideCommand(presentation, 2);
        CommandInvoker commandInvoker = new CommandInvoker();
        commandInvoker.register("goToTwo",goToCommand);

        Assertions.assertDoesNotThrow(()->{commandInvoker.execute("goToTwo");});
    }
}