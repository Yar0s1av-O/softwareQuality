package com.mycompany.commands;
import java.util.HashMap;
import java.util.Map;

public class CommandInvoker
{
    private final Map<String, Command> commandMap = new HashMap<>();

    public void register(String name, Command command)
    {
        commandMap.put(name, command);
    }

    public void execute(String name)
    {
        Command command = commandMap.get(name);

        if (command != null)
        {
            command.execute();
        }
    }
}
