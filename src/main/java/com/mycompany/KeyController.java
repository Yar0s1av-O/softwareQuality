package com.mycompany;

import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

/**
 * KeyController listens to keyboard events and updates the presentation accordingly.
 * It controls slide navigation and application exit through key input.
 *
 * @author Ian F. Darwin, ian@darwinsys.com,
 * Gert Florijn, Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */
public class KeyController extends KeyAdapter
{
    private final Presentation presentation;

    // Constructor receives the presentation model to control
    public KeyController(Presentation p)
    {
        presentation = p;
    }

    @Override
    public void keyPressed(KeyEvent keyEvent)
    {
        // Handle specific keys to control slide navigation and exit
        switch (keyEvent.getKeyCode())
        {
            // Advance to the next slide
            case KeyEvent.VK_PAGE_DOWN:
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_ENTER:
            case '+':
                presentation.nextSlide();
                break;

            // Go back to the previous slide
            case KeyEvent.VK_PAGE_UP:
            case KeyEvent.VK_UP:
            case '-':
                presentation.prevSlide();
                break;

            // Exit the application
            case 'q':
            case 'Q':
                System.exit(0);
                break;

            // Ignore other keys
            default:
                break;
        }
    }
}
