package com.mycompany.commands;
import com.mycompany.commands.Command;

import com.mycompany.Presentation;

// Concrete Command: NewPresentationCommand
public class NewPresentationCommand implements Command {
    private Presentation presentation;

    public NewPresentationCommand(Presentation presentation) {
        this.presentation = presentation;
    }

    @Override
    public void execute() {
        presentation.clear();
    }
}
