package com.mycompany.commands;

// Concrete Command: OpenPresentationCommand
import com.mycompany.Accessor;
import com.mycompany.Presentation;
import com.mycompany.XMLAccessor;
import com.mycompany.commands.Command;

import java.awt.Frame;
import java.io.IOException;
import java.net.URL;
import javax.swing.JOptionPane;
import java.net.URL;
import java.net.URISyntaxException;


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

            URL url = getClass().getClassLoader().getResource("test.xml");
            if (url == null) {
                throw new IOException("test.xml not found in resources");
            }

            String path = new java.io.File(url.toURI()).getAbsolutePath();
            xmlAccessor.loadFile(presentation, path);
            presentation.setSlideNumber(0);
        } catch (IOException | URISyntaxException exc) {
            JOptionPane.showMessageDialog(parent, "IO Exception: " + exc,
                    "Load Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}