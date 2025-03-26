package com.mycompany.commands;
import com.mycompany.commands.Command;

// Concrete Command: GoToSlideCommand
import com.mycompany.Presentation;

import java.awt.Frame;
import javax.swing.JOptionPane;

public class GoToSlideCommand implements Command
{
    private Presentation presentation;
    private int slideNumber;

    public GoToSlideCommand(Presentation presentation, int slideNumber) {
        this.presentation = presentation;
        this.slideNumber = slideNumber;
    }

    @Override
    public void execute() {
        presentation.setSlideNumber(slideNumber);
    }
}

