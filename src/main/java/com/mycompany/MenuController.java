package com.mycompany;

import com.mycompany.commands.*;

import java.awt.*;
import javax.swing.*;

/**
 * MenuController sets up the application's menu bar and connects menu items to their
 * respective commands using the Command pattern.
 */
public class MenuController extends MenuBar
{
    private final Frame parent;
    private final Presentation presentation;
    private final CommandInvoker invoker;
    private static final long serialVersionUID = 227L;

    // Constants for menu labels and commands
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

    public MenuController(Frame frame, Presentation pres, CommandInvoker invoker)
    {
        this.parent = frame;
        this.presentation = pres;
        this.invoker = invoker;

        // Register commands to the invoker
        invoker.register(OPEN, new OpenPresentationCommand(presentation, parent));
        invoker.register(NEW, new NewPresentationCommand(presentation));
        invoker.register(SAVE, new SavePresentationCommand(presentation, parent));
        invoker.register(EXIT, new ExitCommand());
        invoker.register(NEXT, new NextSlideCommand(presentation));
        invoker.register(PREV, new PrevSlideCommand(presentation));
        invoker.register(ABOUT, new ShowAboutBoxCommand(parent));

        // File Menu
        Menu fileMenu = new Menu(FILE);
        fileMenu.add(createMenuItem(OPEN));
        fileMenu.add(createMenuItem(NEW));
        fileMenu.add(createMenuItem(SAVE));
        fileMenu.addSeparator();
        fileMenu.add(createMenuItem(EXIT));
        add(fileMenu);

        // View Menu
        Menu viewMenu = new Menu(VIEW);
        viewMenu.add(createMenuItem(NEXT));
        viewMenu.add(createMenuItem(PREV));

        // Go To Slide option with input dialog
        MenuItem goToItem = mkMenuItem(GOTO);
        goToItem.addActionListener(e -> {
            String pageNumberStr = JOptionPane.showInputDialog(PAGENR);
            try {
                int pageNumber = Integer.parseInt(pageNumberStr);
                new GoToSlideCommand(presentation, pageNumber - 1).execute();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(parent, "Invalid number!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        viewMenu.add(goToItem);
        add(viewMenu);

        // Help Menu
        Menu helpMenu = new Menu(HELP);
        helpMenu.add(createMenuItem(ABOUT));
        setHelpMenu(helpMenu);
    }

    /**
     * Helper method to create a menu item with an action bound to a command key.
     */
    private MenuItem createMenuItem(String commandKey)
    {
        MenuItem item = mkMenuItem(commandKey);
        item.addActionListener(e -> invoker.execute(commandKey));
        return item;
    }

    /**
     * Helper method to create a menu item with a keyboard shortcut.
     */
    private MenuItem mkMenuItem(String name)
    {
        return new MenuItem(name, new MenuShortcut(name.charAt(0)));
    }
}
