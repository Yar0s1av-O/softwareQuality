package com.mycompany;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class PresentationFileManager {

    public static void loadPresentation(Presentation presentation, String resourcePath)
            throws IOException, URISyntaxException {
        Accessor xmlAccessor = new XMLAccessor();
        presentation.clear();

        URL url = PresentationFileManager.class.getClassLoader().getResource(resourcePath);
        if (url == null) {
            throw new IOException(resourcePath + " not found in resources");
        }

        String path = new java.io.File(url.toURI()).getAbsolutePath();
        xmlAccessor.loadFile(presentation, path);
        presentation.setSlideNumber(0);
    }

    public static void savePresentation(Presentation presentation, String filename)
            throws IOException {
        Accessor xmlAccessor = new XMLAccessor();
        xmlAccessor.saveFile(presentation, filename);
    }
}
