package com.mycompany.commands;
import com.mycompany.commands.Command;

// Concrete Command: SavePresentationCommand
import com.mycompany.Accessor;
import com.mycompany.Presentation;
import com.mycompany.XMLAccessor;

import java.awt.Frame;
import java.io.IOException;
import javax.swing.JOptionPane;

public class SavePresentationCommand implements Command {
    private Presentation presentation;
    private Frame parent;

    public SavePresentationCommand(Presentation presentation, Frame parent) {
        this.presentation = presentation;
        this.parent = parent;
    }

    @Override
    public void execute() {
        Accessor xmlAccessor = new XMLAccessor();
        try {
            xmlAccessor.saveFile(presentation, "dump.xml");
        } catch (IOException exc) {
            JOptionPane.showMessageDialog(parent, "IO Exception: " + exc,
                    "Save Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
