package com.mycompany;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccessorTest
{

    @Test
    void testGetDemoAccessorReturnsDemoPresentation()
    {
        Accessor accessor = Accessor.getDemoAccessor();
        assertNotNull(accessor, "Accessor should not be null");
        assertTrue(accessor instanceof DemoPresentation, "Accessor should be an instance of DemoPresentation");
    }

    @Test
    void testSaveFileThrowsException()
    {
        Accessor accessor = Accessor.getDemoAccessor();
        Presentation presentation = new Presentation();

        Exception exception = assertThrows(UnsupportedOperationException.class, () -> {
            accessor.saveFile(presentation, "test.xml");
        });

        assertEquals("Save not supported for demo presentation", exception.getMessage());
    }
}
