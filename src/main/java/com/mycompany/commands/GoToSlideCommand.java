package com.mycompany.commands;
import com.mycompany.Presentation;


public class GoToSlideCommand implements Command
{
    private final Presentation presentation;
    private final int slideNumber;

    public GoToSlideCommand(Presentation presentation, int slideNumber)
    {
        this.presentation = presentation;
        this.slideNumber = slideNumber;
    }

    @Override
    public void execute()
    {
        presentation.setSlideNumber(slideNumber);
    }
}

