package com.mycompany;

import com.mycompany.slidemodel.BitmapItem;
import com.mycompany.slidemodel.Slide;

import java.io.IOException;

/**
 * A built-in demo presentation accessor.
 * This class is used when the application is launched without a specific file.
 * It loads a hardcoded presentation into memory for demonstration purposes.
 *
 * This subclass of Accessor overrides the file loading and saving logic.
 * Saving is not supported for the demo presentation.
 *
 * @author Ian F. Darwin, ian@darwinsys.com,
 *         Gert Florijn, Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */
public class DemoPresentation extends Accessor
{

    /**
     * Loads a hardcoded demonstration presentation into the given Presentation object.
     * This method ignores the filename and fills the presentation with three demo slides.
     *
     * @param presentation   the Presentation object to populate
     * @param unusedFilename ignored parameter (required for interface)
     */
    @Override
    public void loadFile(Presentation presentation, String unusedFilename)
    {
        presentation.setTitle("Demo Presentation");

        Slide slide;

        // Slide 1: Introduction and navigation tips
        slide = new Slide();
        slide.setTitle("JabberPoint");
        slide.append(1, "The Java Presentation Tool");
        slide.append(2, "Copyright (c) 1996-2000: Ian Darwin");
        slide.append(2, "Copyright (c) 2000-now:");
        slide.append(2, "Gert Florijn and Sylvia Stuurman");
        slide.append(4, "Starting JabberPoint without a filename");
        slide.append(4, "shows this presentation");
        slide.append(1, "Navigate:");
        slide.append(3, "Next slide: PgDn or Enter");
        slide.append(3, "Previous slide: PgUp or up-arrow");
        slide.append(3, "Quit: q or Q");
        presentation.append(slide);

        // Slide 2: Demonstrating levels and styling
        slide = new Slide();
        slide.setTitle("Demonstration of levels and styles");
        slide.append(1, "Level 1");
        slide.append(2, "Level 2");
        slide.append(1, "Again level 1");
        slide.append(1, "Level 1 has style number 1");
        slide.append(2, "Level 2 has style number  2");
        slide.append(3, "This is how level 3 looks like");
        slide.append(4, "And this is level 4");
        presentation.append(slide);

        // Slide 3: Final slide and embedded image
        slide = new Slide();
        slide.setTitle("The third slide");
        slide.append(1, "To open a new presentation,");
        slide.append(2, "use File->Open from the menu.");
        slide.append(1, " ");
        slide.append(1, "This is the end of the presentation.");
        slide.append(new BitmapItem(1, "serclogo_fc.jpg"));
        presentation.append(slide);
    }

    /**
     * Saving is intentionally unsupported for the demo presentation.
     *
     * @param p  the Presentation object (unused)
     * @param fn the filename (unused)
     * @throws IOException always thrown to indicate unsupported operation
     */
    @Override
    public void saveFile(Presentation p, String fn) throws IOException
    {
        throw new UnsupportedOperationException("Save not supported for demo presentation");
    }
}
