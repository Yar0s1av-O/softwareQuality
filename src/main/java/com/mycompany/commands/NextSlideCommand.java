package com.mycompany.commands;
import com.mycompany.commands.Command;

import com.mycompany.Presentation;

// Concrete Command: NextSlideCommand
public class NextSlideCommand implements Command {
    private Presentation presentation;

    public NextSlideCommand(Presentation presentation) {
        this.presentation = presentation;
    }

    @Override
    public void execute() {
        presentation.nextSlide();
    }
}
