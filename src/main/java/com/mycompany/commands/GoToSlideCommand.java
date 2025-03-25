package com.mycompany.commands;
import com.mycompany.commands.Command;

// Concrete Command: GoToSlideCommand
import com.mycompany.Presentation;

import java.awt.Frame;
import javax.swing.JOptionPane;

public class GoToSlideCommand implements Command {
    private Presentation presentation;
    private Frame parent;

    public GoToSlideCommand(Presentation presentation, Frame parent) {
        this.presentation = presentation;
        this.parent = parent;
    }

    @Override
    public void execute() {
        String pageNumberStr = JOptionPane.showInputDialog(parent, "Page number?");
        if (pageNumberStr != null && !pageNumberStr.isEmpty()) {
            int pageNumber = Integer.parseInt(pageNumberStr);
            presentation.setSlideNumber(pageNumber - 1);
        }
    }
}

