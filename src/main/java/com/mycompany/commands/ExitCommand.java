package com.mycompany.commands;
import com.mycompany.commands.Command;

import com.mycompany.commands.Command;  // <--- MUST BE THERE


// Concrete Command: ExitCommand
public class ExitCommand implements Command {
    @Override
    public void execute() {
        System.exit(0);
    }
}