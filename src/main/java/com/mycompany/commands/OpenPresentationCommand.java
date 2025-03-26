package com.mycompany.commands;

import com.mycompany.Presentation;
import com.mycompany.PresentationFileManager;
import com.mycompany.commands.Command;

import java.awt.Frame;
import java.io.IOException;
import java.net.URISyntaxException;
import javax.swing.JOptionPane;

public class OpenPresentationCommand implements Command {
    private final Presentation presentation;
    private final Frame parent;

    public OpenPresentationCommand(Presentation presentation, Frame parent) {
        this.presentation = presentation;
        this.parent = parent;
    }

    @Override
    public void execute() {
        try {
            PresentationFileManager.loadPresentation(presentation, "test.xml");
        } catch (IOException | URISyntaxException exc) {
            JOptionPane.showMessageDialog(parent, "IO Exception: " + exc,
                    "Load Error", JOptionPane.ERROR_MESSAGE);
            exc.printStackTrace(); // Optional: helps with debugging
        }
    }
}
