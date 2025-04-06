package com.mycompany.commands;

import com.mycompany.Presentation;
import com.mycompany.XMLAccessor;
import com.mycompany.commands.OpenPresentationCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.MockedConstruction;
import org.mockito.MockedStatic;


import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import static org.mockito.Mockito.*;


public class OpenPresentationCommandTest
{

    private Presentation mockPresentation;
    private Frame mockParent;

    @BeforeEach
    public void setUp() {
        mockPresentation = mock(Presentation.class);
        mockParent = mock(Frame.class);
    }

    @Test
    void openPresentationCommand_doesNotThrow(){
        Presentation presentation = mock(Presentation.class);
        Frame frame = mock(Frame.class);

        Assertions.assertDoesNotThrow(()->{new OpenPresentationCommand(presentation, frame);});
    }

    @Test
    public void testExecute_FileChosen_LoadsFileAndRepaints() throws Exception {
        File mockFile = mock(File.class);

        try (
                MockedConstruction<JFileChooser> chooserMocked = mockConstruction(JFileChooser.class,
                        (mock, context) -> {
                            when(mock.showOpenDialog(mockParent)).thenReturn(JFileChooser.APPROVE_OPTION);
                            when(mock.getSelectedFile()).thenReturn(mockFile);
                            when(mockFile.getAbsolutePath()).thenReturn("mock/path/file.xml");
                        });
                MockedConstruction<XMLAccessor> accessorMocked = mockConstruction(XMLAccessor.class,
                        (mock, context) -> {
                            doNothing().when(mock).loadFile(mockPresentation, "mock/path/file.xml");
                        })
        ) {
            OpenPresentationCommand command = new OpenPresentationCommand(mockPresentation, mockParent);
            command.execute();

            verify(mockPresentation).setSlideNumber(0);
            verify(mockParent).repaint();
        }
    }


    @Test
    public void testExecute_FileChosen_IOException_ShowsErrorDialog() throws Exception {
        File mockFile = mock(File.class);

        try (
                MockedConstruction<JFileChooser> chooserMocked = mockConstruction(JFileChooser.class,
                        (mock, context) -> {
                            when(mock.showOpenDialog(mockParent)).thenReturn(JFileChooser.APPROVE_OPTION);
                            when(mock.getSelectedFile()).thenReturn(mockFile);
                            when(mockFile.getAbsolutePath()).thenReturn("mock/path/file.xml");
                        });
                MockedConstruction<XMLAccessor> accessorMocked = mockConstruction(XMLAccessor.class,
                        (mock, context) -> {
                            doThrow(new IOException("Load failed")).when(mock).loadFile(any(), anyString());
                        });
                MockedStatic<JOptionPane> optionPaneMocked = mockStatic(JOptionPane.class)
        ) {
            OpenPresentationCommand command = new OpenPresentationCommand(mockPresentation, mockParent);
            command.execute();

            optionPaneMocked.verify(() ->
                    JOptionPane.showMessageDialog(
                            eq(mockParent),
                            contains("Load failed"),
                            eq("Open Error"),
                            eq(JOptionPane.ERROR_MESSAGE)
                    )
            );
        }
    }

    @Test
    public void testExecute_Cancelled_DoesNothing() {
        try (
                MockedConstruction<JFileChooser> chooserMocked = mockConstruction(JFileChooser.class,
                        (mock, context) -> {
                            when(mock.showOpenDialog(mockParent)).thenReturn(JFileChooser.CANCEL_OPTION);
                        });
                MockedConstruction<XMLAccessor> accessorMocked = mockConstruction(XMLAccessor.class)
        ) {
            OpenPresentationCommand command = new OpenPresentationCommand(mockPresentation, mockParent);
            command.execute();

            verifyNoInteractions(mockPresentation);
            verify(mockParent, never()).repaint();
        }
    }
}
