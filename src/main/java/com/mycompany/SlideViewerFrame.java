
package com.mycompany;

import com.mycompany.commands.CommandInvoker;
import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import javax.swing.JFrame;

/**
 * SlideViewerFrame sets up the main application window that displays slides.
 * It links the presentation model with the GUI components.
 */
public class SlideViewerFrame extends JFrame
{
    private static final long serialVersionUID = 3227L;
    private static final String JABTITLE = "Jabberpoint 1.6 - OU";
    public static final int WIDTH = 1200;
    public static final int HEIGHT = 800;

    /**
     * Constructor initializes the window, sets up the slide viewer component,
     * and registers it as an observer of the presentation.
     *
     * @param title         The title of the window.
     * @param presentation  The presentation to be displayed.
     */
    public SlideViewerFrame(String title, Presentation presentation)
    {
        super(title);

        SlideViewerComponent slideViewerComponent = new SlideViewerComponent(presentation, this);
        presentation.addObserver(slideViewerComponent); // Observer pattern in action
        setupWindow(slideViewerComponent, presentation);
    }

    /**
     * Configures the window: sets title, size, behavior, menu, and key listener.
     *
     * @param slideViewerComponent  The visual component responsible for rendering slides.
     * @param presentation          The presentation model passed to input handlers and menu.
     */
    public void setupWindow(SlideViewerComponent slideViewerComponent, Presentation presentation)
    {
        setTitle(JABTITLE);

        // Close the application when the window is closed
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e)
            {
                System.exit(0);
            }
        });

        // Add the component that renders slides to the main content area
        getContentPane().add(slideViewerComponent);

        // Add keyboard controls for slide navigation
        addKeyListener(new KeyController(presentation));

        // Create and attach menu bar with command actions
        CommandInvoker invoker = new CommandInvoker();
        setMenuBar(new MenuController(this, presentation, invoker));

        // Set window dimensions and make it visible
        setSize(new Dimension(WIDTH, HEIGHT));
        setVisible(true);
    }
}
