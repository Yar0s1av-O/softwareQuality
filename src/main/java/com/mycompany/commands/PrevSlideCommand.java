package com.mycompany.commands;
import com.mycompany.Presentation;

public class PrevSlideCommand implements Command {
    private final Presentation presentation;

    public PrevSlideCommand(Presentation presentation) {
        this.presentation = presentation;
    }

    @Override
    public void execute() {
        presentation.prevSlide();
    }
}
