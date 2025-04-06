package com.mycompany.commands;

import java.util.HashMap;
import java.util.Map;

/**
 * CommandInvoker manages and executes commands in a decoupled way.
 * It stores command instances in a map, enabling dynamic invocation based on string keys.
 */
public class CommandInvoker
{
    // Stores command name (as String) mapped to the actual Command object
    private final Map<String, Command> commandMap = new HashMap<>();

    /**
     * Getter for the command map.
     * Useful for inspecting or modifying registered commands externally (e.g., in tests).
     */
    public Map<String, Command> getCommandMap()
    {
        return this.commandMap;
    }

    /**
     * Registers a command instance with a given name.
     *
     * @param name    the key (e.g., "Open", "Exit")
     * @param command the command object implementing the Command interface
     */
    public void register(String name, Command command)
    {
        commandMap.put(name, command);
    }

    /**
     * Executes a command based on its registered name.
     *
     * @param name the key used to retrieve and execute the command
     */
    public void execute(String name)
    {
        Command command = commandMap.get(name);

        if (command != null)
        {
            command.execute();
        }
    }
}
