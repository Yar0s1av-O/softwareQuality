package com.mycompany;

import com.mycompany.commands.CommandInvoker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.Frame;
import java.awt.GraphicsEnvironment;
import java.awt.Menu;
import java.awt.MenuItem;

import static org.junit.jupiter.api.Assumptions.assumeFalse;
import static org.junit.jupiter.api.Assertions.*;

class MenuControllerTest {

    private MenuController menuController;
    private Presentation mockPresentation;
    private CommandInvoker invoker;

    @BeforeEach
    void setUp() {
        // Skip GUI tests in headless environments like CI
        assumeFalse(GraphicsEnvironment.isHeadless(), "Skipping GUI tests in headless environment");

        mockPresentation = new Presentation();
        invoker = new CommandInvoker();

        Frame dummyFrame = new Frame();
        menuController = new MenuController(dummyFrame, mockPresentation, invoker);
    }

    @Test
    void testFileMenuExists() {
        Menu fileMenu = menuController.getMenu(0);
        assertNotNull(fileMenu);
        assertEquals("File", fileMenu.getLabel());
    }

    @Test
    void testHelpMenuExists() {
        Menu helpMenu = menuController.getHelpMenu();
        assertNotNull(helpMenu);
        assertEquals("Help", helpMenu.getLabel());
    }

    @Test
    void testFileMenuHasExitItem() {
        Menu fileMenu = menuController.getMenu(0);
        boolean hasExit = false;

        for (int i = 0; i < fileMenu.getItemCount(); i++) {
            MenuItem item = fileMenu.getItem(i);
            if ("Exit".equals(item.getLabel())) {
                hasExit = true;
                break;
            }
        }

        assertTrue(hasExit, "Exit menu item should exist in File menu");
    }
}
