package com.mycompany;

import com.mycompany.slidemodel.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.*;
import java.util.Vector;

public class XMLAccessor extends Accessor {
    private static final String SHOWTITLE = "showtitle";
    private static final String SLIDE = "slide";
    private static final String SLIDETITLE = "title";
    private static final String ITEM = "item";
    private static final String KIND = "kind";
    private static final String LEVEL = "level";

    @Override
    public void loadFile(Presentation presentation, String filename) throws IOException {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(filename));
            Element root = document.getDocumentElement();

            presentation.setTitle(getTextContent(root, SHOWTITLE));

            NodeList slides = root.getElementsByTagName(SLIDE);
            for (int i = 0; i < slides.getLength(); i++) {
                Element slideElement = (Element) slides.item(i);
                Slide slide = new Slide();
                slide.setTitle(getTextContent(slideElement, SLIDETITLE));
                loadSlideItems(slide, slideElement);
                presentation.append(slide);
            }

        } catch (ParserConfigurationException | SAXException e) {
            throw new IOException("Error parsing XML: " + e.getMessage());
        }
    }

    private void loadSlideItems(Slide slide, Element slideElement) {
        NodeList items = slideElement.getElementsByTagName(ITEM);

        for (int i = 0; i < items.getLength(); i++) {
            Element item = (Element) items.item(i);
            String kind = item.getAttribute(KIND);
            String levelText = item.getAttribute(LEVEL);
            String content = item.getTextContent();

            try {
                SlideItem slideItem = null;

                if (kind.equals("text")) {
                    slideItem = new TextItem(Integer.parseInt(levelText), content);
                } else if (kind.equals("image")) {
                    slideItem = new BitmapItem(Integer.parseInt(levelText), content);
                }

                if (slideItem != null) {
                    slide.append(slideItem);
                }
            } catch (Exception e) {
                System.out.println("Error loading slide item: " + e.getMessage());
            }
        }
    }

    private String getTextContent(Element parent, String tagName) {
        NodeList nodes = parent.getElementsByTagName(tagName);
        if (nodes.getLength() > 0) {
            return nodes.item(0).getTextContent();
        }
        return "";
    }

    @Override
    public void saveFile(Presentation presentation, String filename) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println("<?xml version=\"1.0\"?>");
            writer.println("<!DOCTYPE presentation SYSTEM \"jabberpoint.dtd\">");
            writer.println("<presentation>");
            writer.println("  <showtitle>" + presentation.getTitle() + "</showtitle>");

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

            writer.println("</presentation>");
        }
    }
}
