package com.mycompany.commands;

import com.mycompany.AboutBox;

import java.awt.Frame;

/**
 * Command to display the "About" dialog of the JabberPoint application.
 */
public class ShowAboutBoxCommand implements Command {

    private final Frame parent; // The parent frame to anchor the About dialog to

    /**
     * Constructor that takes the parent frame for dialog positioning.
     *
     * @param parent the frame from which the dialog is displayed
     */
    public ShowAboutBoxCommand(Frame parent) {
        this.parent = parent;
    }

    /**
     * Executes the command by invoking the AboutBox's static show method.
     */
    @Override
    public void execute() {
        AboutBox.show(parent); // Display the About dialog
    }
}
