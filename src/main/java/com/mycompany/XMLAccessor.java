package com.mycompany;

import com.mycompany.io.SlideItemLoader;
import com.mycompany.slidemodel.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.xml.parsers.*;
import java.io.*;
import java.util.Vector;

/**
 * XMLAccessor is responsible for loading and saving a Presentation from/to an XML file.
 * It parses the XML structure and maps it to Java objects such as Slide and SlideItem.
 */
public class XMLAccessor extends Accessor {
    // Constants for XML tag and attribute names
    private static final String SHOWTITLE = "showtitle";
    private static final String SLIDE = "slide";
    private static final String SLIDETITLE = "title";
    private static final String ITEM = "item";
    private static final String KIND = "kind";
    private static final String LEVEL = "level";

    /**
     * Loads a presentation from an XML file.
     *
     * @param presentation The Presentation object to populate.
     * @param filename     Path to the XML file.
     * @throws IOException If parsing or file access fails.
     */
    @Override
    public void loadFile(Presentation presentation, String filename) throws IOException {
        try {
            // Set up XML parser
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(filename));
            Element root = document.getDocumentElement();

            // Clear current slides and set the presentation title
            presentation.clear();
            presentation.setTitle(getTextContent(root, SHOWTITLE));

            // Parse and add all slide elements
            NodeList slides = root.getElementsByTagName(SLIDE);
            addSlidesToPresentation(presentation, slides);

        } catch (ParserConfigurationException | SAXException e) {
            throw new IOException("Error parsing XML: " + e.getMessage());
        }
    }

    /**
     * Parses all slide elements and adds them to the presentation.
     */
    private void addSlidesToPresentation(Presentation presentation, NodeList slides) {
        for (int i = 0; i < slides.getLength(); i++) {
            Element slideElement = (Element) slides.item(i);
            Slide slide = new Slide();
            slide.setTitle(getTextContent(slideElement, SLIDETITLE));

            // Load all slide items (text/image) for each slide
            NodeList slideItems = slideElement.getElementsByTagName(ITEM);
            for (int j = 0; j < slideItems.getLength(); j++) {
                Element item = (Element) slideItems.item(j);
                loadSlideItem(slide, item);
            }

            presentation.append(slide);
        }
    }

    /**
     * Converts a single XML item element into a SlideItem and adds it to the slide.
     */
    protected void loadSlideItem(Slide slide, Element item) {
        NamedNodeMap attributes = item.getAttributes();
        String leveltext = attributes.getNamedItem("level").getTextContent();
        String type = attributes.getNamedItem("kind").getTextContent();
        String content = item.getTextContent();

        try {
            SlideItem slideItem = SlideItemLoader.loadSlideItem(type, leveltext, content);
            slide.append(slideItem);
        } catch (IOException e) {
            System.err.println("Failed to load slide item: " + e.getMessage());
        }
    }

    /**
     * Helper method to get the text content of a specific child element.
     */
    private String getTextContent(Element parent, String tagName) {
        NodeList nodes = parent.getElementsByTagName(tagName);
        if (nodes.getLength() > 0) {
            return nodes.item(0).getTextContent();
        }
        return "";
    }

    /**
     * Saves a presentation to an XML file using basic formatting.
     *
     * @param presentation The presentation to save.
     * @param filename     The output file path.
     * @throws IOException If writing fails.
     */
    @Override
    public void saveFile(Presentation presentation, String filename) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println("<?xml version=\"1.0\"?>");
            writer.println("<!DOCTYPE presentation SYSTEM \"jabberpoint.dtd\">");
            writer.println("<presentation>");
            writer.println("  <showtitle>" + presentation.getTitle() + "</showtitle>");
            printSlides(presentation, writer);
            writer.println("</presentation>");
        }
    }

    /**
     * Writes all slides and their items to the XML output.
     */
    private static void printSlides(Presentation presentation, PrintWriter writer) {
        for (int i = 0; i < presentation.getSize(); i++) {
            Slide slide = presentation.getSlide(i);
            writer.println("  <slide>");
            writer.println("    <title>" + slide.getTitle() + "</title>");

            Vector<SlideItem> items = slide.getSlideItems();
            for (SlideItem item : items) {
                if (item instanceof TextItem) {
                    writer.println("    <item kind=\"text\" level=\"" + item.getLevel() + "\">" +
                            ((TextItem) item).getText() + "</item>");
                } else if (item instanceof BitmapItem) {
                    writer.println("    <item kind=\"image\" level=\"" + item.getLevel() + "\">" +
                            ((BitmapItem) item).getName() + "</item>");
                }
            }

            writer.println("  </slide>");
        }
    }
}
