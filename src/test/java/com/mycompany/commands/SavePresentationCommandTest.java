package com.mycompany.commands;

import com.mycompany.Presentation;
import com.mycompany.PresentationFileManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static org.mockito.Mockito.*;
public class SavePresentationCommandTest
{
    @Test
    void savePresentationCommand_doesNotThrow(){
        Presentation presentation = mock(Presentation.class);
        Frame frame = mock(Frame.class);

        Assertions.assertDoesNotThrow(()->{new SavePresentationCommand(presentation, frame);});
    }

    @Test
    void execute(){
        Presentation presentation = mock(Presentation.class);
        Frame frame = mock(Frame.class);
        Command command = new SavePresentationCommand(presentation, frame);

        Assertions.assertDoesNotThrow(()->{command.execute();});
    }

    @Test
    public void testExecute_WhenIOException_ShouldShowErrorDialog() throws IOException {
        IOException testException = new IOException("Disk error");
        Presentation mockPresentation = mock(Presentation.class);
        Frame mockParent = mock(Frame.class);

        try (
                MockedStatic<PresentationFileManager> pfmMock = mockStatic(PresentationFileManager.class);
                MockedStatic<JOptionPane> optionPaneMock = mockStatic(JOptionPane.class)
        ) {
            pfmMock.when(() -> PresentationFileManager.savePresentation(mockPresentation, "dump.xml"))
                    .thenThrow(testException);

            SavePresentationCommand command = new SavePresentationCommand(mockPresentation, mockParent);
            command.execute();

            optionPaneMock.verify(() -> JOptionPane.showMessageDialog(
                    eq(mockParent),
                    contains("IO Exception:"),
                    eq("Save Error"),
                    eq(JOptionPane.ERROR_MESSAGE)
            ));
        }
    }

}
