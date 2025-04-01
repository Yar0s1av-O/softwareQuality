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
 * <p>SlideViewerComponent is a graphical component that can show slides.</p>
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class SlideViewerComponent extends JComponent implements MySubscriber
{

    private Slide slide;
    private Font labelFont = null;
    private Presentation presentation = null;
    private JFrame frame = null;
    private static final long serialVersionUID = 227L;
    private static final Color BGCOLOR = Color.white;
    private static final Color COLOR = Color.black;
    private static final String FONTNAME = "Dialog";
    private static final int FONTSTYLE = Font.BOLD;
    private static final int FONTHEIGHT = 10;
    private static final int XPOS = 1100;
    private static final int YPOS = 20;

    public SlideViewerComponent(Presentation pres, JFrame frame)
    {
        this.setBackground(this.BGCOLOR);
        this.presentation = pres;
        this.labelFont = new Font(this.FONTNAME, this.FONTSTYLE, this.FONTHEIGHT);
        this.frame = frame;
    }

    public Dimension getPreferredSize()
    {
        return new Dimension(Slide.WIDTH, Slide.HEIGHT);
    }

    @Override
    public void update(Presentation presentation, Slide data)
    {
        if (data == null)
        {
            repaint();

            return;
        }

        this.presentation = presentation;
        this.slide = data;
        repaint();
        this.frame.setTitle(presentation.getTitle());
    }

    public void paintComponent(Graphics g)
    {
        g.setColor(this.BGCOLOR);
        g.fillRect(0, 0, getSize().width, getSize().height);

        if (this.presentation.getSlideNumber() < 0 || this.slide == null)
        {
            return;
        }

        g.setFont(this.labelFont);
        g.setColor(this.COLOR);
        g.drawString("Slide " + (1 + this.presentation.getSlideNumber()) + " of " +
                this.presentation.getSize(), this.XPOS, this.YPOS);
        Rectangle area = new Rectangle(0, this.YPOS, getWidth(), (getHeight() - this.YPOS));

        slide.draw(g, area, this);
    }
}
