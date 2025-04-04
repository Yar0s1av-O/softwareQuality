package com.mycompany;

import com.mycompany.Presentation;
import com.mycompany.slidemodel.Slide;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

class XMLAccessorTest
{
    @Test
    void testLoadFile_shouldLoadTitleAndSlides() throws Exception
    {
        Presentation presentation = new Presentation();
        XMLAccessor accessor = new XMLAccessor();

        // Load test.xml from the classpath safely
        URL resourceUrl = getClass().getClassLoader().getResource("test.xml");
        assertNotNull(resourceUrl, "test.xml not found in classpath!");

        File file = new File(resourceUrl.toURI());
        accessor.loadFile(presentation, file.getAbsolutePath());

        assertEquals("XML-Based Presentation for Jabberpoint", presentation.getTitle());
        assertTrue(presentation.getSize() > 0);
    }
}
