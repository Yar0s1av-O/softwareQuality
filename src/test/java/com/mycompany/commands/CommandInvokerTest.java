package com.mycompany.commands;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

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
}