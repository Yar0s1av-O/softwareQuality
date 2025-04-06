package com.mycompany.commands;

import com.mycompany.AboutBox;
import com.mycompany.Presentation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.awt.*;

import static org.mockito.Mockito.*;

public class ShowAboutBoxCommandTest
{
    @Test
    void ShowAboutBoxCommand_doesNotThrow()
    {
        Frame frame = mock(Frame.class);

        Assertions.assertDoesNotThrow(() -> {
            new ShowAboutBoxCommand(frame);
        });
    }

    @Test
    public void testExecute_ShouldCallAboutBoxShow()
    {
        Frame mockFrame = mock(Frame.class);
        try (MockedStatic<AboutBox> aboutBoxMockedStatic = mockStatic(AboutBox.class))
        {
            ShowAboutBoxCommand command = new ShowAboutBoxCommand(mockFrame);

            command.execute();

            aboutBoxMockedStatic.verify(() -> AboutBox.show(mockFrame), times(1));
        }
    }
}
