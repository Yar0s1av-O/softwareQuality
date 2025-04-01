package com.mycompany;

import com.mycompany.commands.CommandInvoker;
import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import javax.swing.JFrame;

/**
 * <p>The application window for a slideviewcomponent</p>
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class SlideViewerFrame extends JFrame
{
    private static final long serialVersionUID = 3227L;
    private static final String JABTITLE = "Jabberpoint 1.6 - OU";
    public final static int WIDTH = 1200;
    public final static int HEIGHT = 800;

    public SlideViewerFrame(String title, Presentation presentation)
    {
        super(title);

        SlideViewerComponent slideViewerComponent = new SlideViewerComponent(presentation, this);
        presentation.addObserver(slideViewerComponent);
        setupWindow(slideViewerComponent, presentation);
    }

    public void setupWindow(SlideViewerComponent
                                    slideViewerComponent, Presentation presentation)
    {
        setTitle(this.JABTITLE);
        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                System.exit(0);
            }
        });
        getContentPane().add(slideViewerComponent);
        addKeyListener(new KeyController(presentation));
        CommandInvoker invoker = new CommandInvoker();
        setMenuBar(new MenuController(this, presentation, invoker));
        setSize(new Dimension(this.WIDTH, this.HEIGHT));
        setVisible(true);
    }
}
