package com.mycompany.commands;

import com.mycompany.Presentation;
import com.mycompany.XMLAccessor;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class OpenPresentationCommand implements Command
{
    private final Presentation presentation;
    private final Frame parent;

    public OpenPresentationCommand(Presentation presentation, Frame parent)
    {
        this.presentation = presentation;
        this.parent = parent;
    }

    @Override
    public void execute()
    {
        JFileChooser fileChooser = new JFileChooser();

        fileChooser.setDialogTitle("Open JabberPoint Presentation");
        int result = fileChooser.showOpenDialog(parent);

        if (result == JFileChooser.APPROVE_OPTION)
        {
            File selectedFile = fileChooser.getSelectedFile();

            try
            {
                new XMLAccessor().loadFile(presentation, selectedFile.getAbsolutePath());
                presentation.setSlideNumber(0);
                parent.repaint();
            } catch (IOException e)
            {
                JOptionPane.showMessageDialog(parent,
                        "Failed to open file: " + e.getMessage(),
                        "Open Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
