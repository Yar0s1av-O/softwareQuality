package com.mycompany;

import com.mycompany.commands.*;

import java.awt.*;
import javax.swing.*;

public class MenuController extends MenuBar
{
    private final Frame parent;
    private final Presentation presentation;
    private final CommandInvoker invoker;
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

    public MenuController(Frame frame, Presentation pres, CommandInvoker invoker)
    {
        this.parent = frame;
        this.presentation = pres;
        this.invoker = invoker;

        invoker.register(OPEN, new OpenPresentationCommand(presentation, parent));
        invoker.register(NEW, new NewPresentationCommand(presentation));
        invoker.register(SAVE, new SavePresentationCommand(presentation, parent));
        invoker.register(EXIT, new ExitCommand());
        invoker.register(NEXT, new NextSlideCommand(presentation));
        invoker.register(PREV, new PrevSlideCommand(presentation));
        invoker.register(ABOUT, new ShowAboutBoxCommand(parent));

        Menu fileMenu = new Menu(FILE);
        fileMenu.add(createMenuItem(OPEN));
        fileMenu.add(createMenuItem(NEW));
        fileMenu.add(createMenuItem(SAVE));
        fileMenu.addSeparator();
        fileMenu.add(createMenuItem(EXIT));
        add(fileMenu);

        Menu viewMenu = new Menu(VIEW);
        viewMenu.add(createMenuItem(NEXT));
        viewMenu.add(createMenuItem(PREV));

        MenuItem goToItem = mkMenuItem(GOTO);

        goToItem.addActionListener(e -> {
            String pageNumberStr = JOptionPane.showInputDialog(PAGENR);

            try
            {
                int pageNumber = Integer.parseInt(pageNumberStr);

                new GoToSlideCommand(presentation, pageNumber - 1).execute();
            } catch (NumberFormatException ex)
            {
                JOptionPane.showMessageDialog(parent, "Invalid number!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        viewMenu.add(goToItem);
        add(viewMenu);

        Menu helpMenu = new Menu(HELP);
        helpMenu.add(createMenuItem(ABOUT));

        setHelpMenu(helpMenu);
    }

    private MenuItem createMenuItem(String commandKey)
    {
        MenuItem item = mkMenuItem(commandKey);
        item.addActionListener(e -> invoker.execute(commandKey));

        return item;
    }

    private MenuItem mkMenuItem(String name)
    {
        return new MenuItem(name, new MenuShortcut(name.charAt(0)));
    }
}
