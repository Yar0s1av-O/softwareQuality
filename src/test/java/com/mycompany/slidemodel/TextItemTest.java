package com.mycompany.slidemodel;

import com.mycompany.Style;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TextItemTest {

    private TextItem item;
    private Style style;
    private ImageObserver mockObserver;

    @BeforeEach
    void setUp()
    {
        Style.createStyles(); //  This initializes the styles array

        item = new TextItem(1, "Hello World");
        style = Style.getStyle(1);
        mockObserver = mock(ImageObserver.class);
    }



    @Test
    void testGetTextReturnsText()
    {
        assertEquals("Hello World", item.getText());
    }

    @Test
    void testNullTextReturnsEmptyString() {
        TextItem emptyItem = new TextItem(2, null);
        assertEquals("", emptyItem.getText());
    }

    @Test
    void testToString() {
        assertEquals("TextItem[1,Hello World]", item.toString());
    }

    @Test
    void testAttributedStringContent() {
        AttributedCharacterIterator it = item.getAttributedString(style, 1.0f).getIterator();
        StringBuilder sb = new StringBuilder();
        while (it.getIndex() < it.getEndIndex()) {
            sb.append(it.current());
            it.next();
        }
        assertEquals("Hello World", sb.toString());
    }

    @Test
    void testBoundingBoxIsNotNull() {
        Graphics2D mockGraphics = mock(Graphics2D.class);
        when(mockGraphics.getFontRenderContext()).thenReturn(new FontRenderContext(null, true, true));

        Rectangle box = item.getBoundingBox(mockGraphics, mockObserver, 1.0f, style);

        assertNotNull(box);
        assertTrue(box.width >= 0);
        assertTrue(box.height >= 0);
    }
}
