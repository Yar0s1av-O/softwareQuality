package com.mycompany.commands;

import com.mycompany.Presentation;
import com.mycompany.PresentationFileManager;
import com.mycompany.commands.Command;

import java.awt.Frame;
import java.io.IOException;
import javax.swing.JOptionPane;

public class SavePresentationCommand implements Command {
    private final Presentation presentation;
    private final Frame parent;

    public SavePresentationCommand(Presentation presentation, Frame parent) {
        this.presentation = presentation;
        this.parent = parent;
    }

    @Override
    public void execute() {
        try {
            PresentationFileManager.savePresentation(presentation, "dump.xml");
        } catch (IOException exc) {
            JOptionPane.showMessageDialog(parent, "IO Exception: " + exc,
                    "Save Error", JOptionPane.ERROR_MESSAGE);
            exc.printStackTrace(); // Optional: helps during development
        }
    }
}
