package com.mycompany;

import com.mycompany.slidemodel.Slide;

import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 * SlideViewerComponent is a graphical component responsible for rendering slides
 * within a GUI window. It implements the observer interface to update itself
 * when the presentation changes.
 */
public class SlideViewerComponent extends JComponent implements MySubscriber
{

    private Slide slide;                  // Currently displayed slide
    private Font labelFont = null;       // Font used for slide label text
    private Presentation presentation;   // Reference to the current presentation
    private JFrame frame;                // Reference to the parent frame

    private static final long serialVersionUID = 227L;

    // Styling constants
    private static final Color BGCOLOR = Color.white;
    private static final Color COLOR = Color.black;
    private static final String FONTNAME = "Dialog";
    private static final int FONTSTYLE = Font.BOLD;
    private static final int FONTHEIGHT = 10;

    // Positioning constants for the label text
    private static final int XPOS = 1100;
    private static final int YPOS = 20;

    /**
     * Constructor initializes the viewer with a presentation and its containing frame.
     *
     * @param pres  The presentation to be displayed.
     * @param frame The frame in which this component resides.
     */
    public SlideViewerComponent(Presentation pres, JFrame frame)
    {
        this.setBackground(BGCOLOR);
        this.presentation = pres;
        this.labelFont = new Font(FONTNAME, FONTSTYLE, FONTHEIGHT);
        this.frame = frame;
    }

    /**
     * Returns the preferred size of the component, matching the default slide size.
     */
    public Dimension getPreferredSize()
    {
        return new Dimension(Slide.WIDTH, Slide.HEIGHT);
    }

    /**
     * Updates the viewer when the presentation or slide changes.
     *
     * @param presentation The current presentation.
     * @param data         The slide to be displayed.
     */
    @Override
    public void update(Presentation presentation, Slide data)
    {
        if (data == null)
        {
            repaint();  // Repaint only the background
            return;
        }

        this.presentation = presentation;
        this.slide = data;
        repaint();  // Refresh the component visually
        this.frame.setTitle(presentation.getTitle());  // Update window title
    }

    /**
     * Renders the current slide and its position in the presentation.
     *
     * @param g The graphics context used for drawing.
     */
    public void paintComponent(Graphics g)
    {
        g.setColor(BGCOLOR);
        g.fillRect(0, 0, getSize().width, getSize().height);

        // Skip rendering if no valid slide is set
        if (this.presentation.getSlideNumber() < 0 || this.slide == null)
        {
            return;
        }

        g.setFont(labelFont);
        g.setColor(COLOR);
        g.drawString(
                "Slide " + (1 + this.presentation.getSlideNumber()) + " of " + this.presentation.getSize(),
                XPOS,
                YPOS
        );

        // Define the area in which the slide should be drawn
        Rectangle area = new Rectangle(0, YPOS, getWidth(), getHeight() - YPOS);

        // Delegate the actual slide drawing to the slide itself
        slide.draw(g, area, this);
    }
}
