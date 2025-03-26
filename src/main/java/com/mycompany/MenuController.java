package com.mycompany;

import com.mycompany.commands.Command;
import com.mycompany.commands.GoToSlideCommand;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class MenuController extends MenuBar {

	private final Frame parent;
	private final Presentation presentation;

	private static final long serialVersionUID = 227L;

	protected static final String ABOUT = "About";
	protected static final String FILE = "File";
	protected static final String EXIT = "Exit";
	protected static final String GOTO = "Go to";
	protected static final String HELP = "Help";
	protected static final String NEW = "New";
	protected static final String NEXT = "Next";
	protected static final String OPEN = "Open";
	protected static final String PAGENR = "Page number?";
	protected static final String PREV = "Prev";
	protected static final String SAVE = "Save";
	protected static final String VIEW = "View";

	protected static final String SAVEFILE = "dump.xml";

	protected static final String IOEX = "IO Exception: ";
	protected static final String LOADERR = "Load Error";
	protected static final String SAVEERR = "Save Error";

	public MenuController(Frame frame, Presentation pres) {
		this.parent = frame;
		this.presentation = pres;

		MenuItem menuItem;

		// --- File Menu ---
		Menu fileMenu = new Menu(FILE);

		// Open
		fileMenu.add(menuItem = mkMenuItem(OPEN));
		menuItem.addActionListener(actionEvent -> {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle("Open JabberPoint Presentation");

			int result = fileChooser.showOpenDialog(parent);
			if (result == JFileChooser.APPROVE_OPTION) {
				File selectedFile = fileChooser.getSelectedFile();
				try {
					new XMLAccessor().loadFile(presentation, selectedFile.getAbsolutePath());
					presentation.setSlideNumber(0);
					parent.repaint();
				} catch (IOException e) {
					JOptionPane.showMessageDialog(parent,
							"Failed to open file: " + e.getMessage(),
							"File Error",
							JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				}
			}
		});

		// New
		fileMenu.add(menuItem = mkMenuItem(NEW));
		menuItem.addActionListener(actionEvent -> {
			presentation.clear();
			parent.repaint();
		});

		// Save
		fileMenu.add(menuItem = mkMenuItem(SAVE));
		menuItem.addActionListener(actionEvent -> {
			try {
				new XMLAccessor().saveFile(presentation, SAVEFILE);
			} catch (IOException exc) {
				JOptionPane.showMessageDialog(parent, IOEX + exc,
						SAVEERR, JOptionPane.ERROR_MESSAGE);
			}
		});

		fileMenu.addSeparator();

		// Exit
		fileMenu.add(menuItem = mkMenuItem(EXIT));
		menuItem.addActionListener(actionEvent -> presentation.exit(0));

		add(fileMenu);

		// --- View Menu ---
		Menu viewMenu = new Menu(VIEW);

		// Next
		viewMenu.add(menuItem = mkMenuItem(NEXT));
		menuItem.addActionListener(actionEvent -> presentation.nextSlide());

		// Prev
		viewMenu.add(menuItem = mkMenuItem(PREV));
		menuItem.addActionListener(actionEvent -> presentation.prevSlide());

		// Go To
		viewMenu.add(menuItem = mkMenuItem(GOTO));
		menuItem.addActionListener(actionEvent -> {
			String pageNumberStr = JOptionPane.showInputDialog(parent, PAGENR);
			try {
				int pageNumber = Integer.parseInt(pageNumberStr);
				Command goTo = new GoToSlideCommand(presentation, pageNumber - 1);
				goTo.execute();
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(parent, "Invalid number!", "Error", JOptionPane.ERROR_MESSAGE);
			}
		});

		add(viewMenu);

		// --- Help Menu ---
		Menu helpMenu = new Menu(HELP);
		helpMenu.add(menuItem = mkMenuItem(ABOUT));
		menuItem.addActionListener(actionEvent -> AboutBox.show(parent));
		setHelpMenu(helpMenu);
	}

	// Utility: create menu item with shortcut
	public MenuItem mkMenuItem(String name) {
		return new MenuItem(name, new MenuShortcut(name.charAt(0)));
	}
}
