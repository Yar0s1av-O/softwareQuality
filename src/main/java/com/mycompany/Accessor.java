package com.mycompany;

import java.io.IOException;

/**
 * Abstract base class for loading and saving presentation data.
 * Concrete subclasses are responsible for implementing the actual read/write mechanisms,
 * such as loading from XML or providing demo data.
 *
 * Originally written in Dutch:
 * "Een Accessor maakt het mogelijk om gegevens voor een presentatie te lezen of te schrijven."
 * Translation: "An Accessor makes it possible to read or write data for a presentation."
 *
 * Non-abstract subclasses must implement the `loadFile` and `saveFile` methods.
 *
 * @author Ian F. Darwin, ian@darwinsys.com,
 *         Gert Florijn, Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */
public abstract class Accessor
{
    // Name used for demo presentations
    public static final String DEMO_NAME = "Demonstration presentation";

    // Default file extension for saved presentations
    public static final String DEFAULT_EXTENSION = ".xml";

    /**
     * Returns an Accessor that loads a pre-defined demo presentation.
     *
     * @return a DemoPresentation instance
     */
    public static Accessor getDemoAccessor()
    {
        return new DemoPresentation();
    }

    // Default constructor
    public Accessor()
    {
    }

    /**
     * Abstract method to load a presentation from a file.
     *
     * @param p  the Presentation object to populate
     * @param fn the filename to load from
     * @throws IOException if an I/O error occurs
     */
    abstract public void loadFile(Presentation p, String fn) throws IOException;

    /**
     * Abstract method to save a presentation to a file.
     *
     * @param p  the Presentation object to save
     * @param fn the filename to save to
     * @throws IOException if an I/O error occurs
     */
    abstract public void saveFile(Presentation p, String fn) throws IOException;
}
