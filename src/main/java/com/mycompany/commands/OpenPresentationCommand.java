package com.mycompany.commands;

import com.mycompany.Presentation;
import com.mycompany.XMLAccessor;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Command implementation for opening a presentation file using a file chooser.
 */
public class OpenPresentationCommand implements Command
{
    private final Presentation presentation; // The presentation instance to load data into
    private final Frame parent;              // Parent frame used for dialogs

    /**
     * Constructor that initializes the command with the target presentation and parent frame.
     *
     * @param presentation the presentation object to populate with slide data
     * @param parent       the parent frame for displaying dialog boxes
     */
    public OpenPresentationCommand(Presentation presentation, Frame parent)
    {
        this.presentation = presentation;
        this.parent = parent;
    }

    /**
     * Executes the open command by showing a file chooser and loading the selected file.
     */
    @Override
    public void execute()
    {
        JFileChooser fileChooser = new JFileChooser(); // Dialog for file selection
        fileChooser.setDialogTitle("Open JabberPoint Presentation");

        int result = fileChooser.showOpenDialog(parent);

        if (result == JFileChooser.APPROVE_OPTION)
        {
            File selectedFile = fileChooser.getSelectedFile();

            try
            {
                // Load the selected presentation file into the application
                new XMLAccessor().loadFile(presentation, selectedFile.getAbsolutePath());
                presentation.setSlideNumber(0); // Reset to the first slide
                parent.repaint(); // Refresh the GUI to show the loaded content
            } catch (IOException e)
            {
                // Show error dialog if the file couldn't be loaded
                JOptionPane.showMessageDialog(parent,
                        "Failed to open file: " + e.getMessage(),
                        "Open Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
