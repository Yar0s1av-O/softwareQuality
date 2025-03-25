package com.mycompany.commands;

// Concrete Command: OpenPresentationCommand
import com.mycompany.Accessor;
import com.mycompany.Presentation;
import com.mycompany.XMLAccessor;
import com.mycompany.commands.Command;

import java.awt.Frame;
import java.io.IOException;
import javax.swing.JOptionPane;

public class OpenPresentationCommand implements Command {
    private Presentation presentation;
    private Frame parent;

    public OpenPresentationCommand(Presentation presentation, Frame parent) {
        this.presentation = presentation;
        this.parent = parent;
    }

    @Override
    public void execute() {
        Accessor xmlAccessor = new XMLAccessor();
        try {
            presentation.clear();
            xmlAccessor.loadFile(presentation, "test.xml");
            presentation.setSlideNumber(0);
        } catch (IOException exc) {
            JOptionPane.showMessageDialog(parent, "IO Exception: " + exc,
                    "Load Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
