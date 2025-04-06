package com.mycompany;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Utility class responsible for loading and saving presentations.
 * It abstracts file handling logic away from the core presentation logic.
 */
public class PresentationFileManager {

    /**
     * Loads a presentation from the given resource path into the provided Presentation object.
     * This method expects the resource to be available on the classpath.
     *
     * @param presentation  The presentation object to populate.
     * @param resourcePath  The relative path to the resource (e.g., XML file).
     * @throws IOException if the file cannot be read or found.
     * @throws URISyntaxException if the resource path cannot be converted to a URI.
     */
    public static void loadPresentation(Presentation presentation, String resourcePath)
            throws IOException, URISyntaxException {

        // Use XMLAccessor to handle loading logic
        Accessor xmlAccessor = new XMLAccessor();

        // Clear the current state before loading new data
        presentation.clear();

        // Load resource from the classpath
        URL url = PresentationFileManager.class.getClassLoader().getResource(resourcePath);
        if (url == null) {
            throw new IOException(resourcePath + " not found in resources");
        }

        // Convert URL to file path and load data
        String path = new java.io.File(url.toURI()).getAbsolutePath();
        xmlAccessor.loadFile(presentation, path);

        // Reset slide index to the first slide
        presentation.setSlideNumber(0);
    }

    /**
     * Saves the current presentation to a file.
     *
     * @param presentation  The presentation to be saved.
     * @param filename      The path where the file should be saved.
     * @throws IOException if the file cannot be written.
     */
    public static void savePresentation(Presentation presentation, String filename)
            throws IOException {

        // Use XMLAccessor to save the presentation
        Accessor xmlAccessor = new XMLAccessor();
        xmlAccessor.saveFile(presentation, filename);
    }
}
