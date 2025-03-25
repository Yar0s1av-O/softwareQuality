package com.mycompany.commands;
import com.mycompany.commands.Command;

import com.mycompany.Presentation;

// Concrete Command: PrevSlideCommand
public class PrevSlideCommand implements Command {
    private Presentation presentation;

    public PrevSlideCommand(Presentation presentation) {
        this.presentation = presentation;
    }

    @Override
    public void execute() {
        presentation.prevSlide();
    }
}
