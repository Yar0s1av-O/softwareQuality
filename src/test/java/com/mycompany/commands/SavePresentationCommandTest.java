package com.mycompany.commands;

import com.mycompany.Presentation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.mockito.Mockito.*;
public class SavePresentationCommandTest
{
    @Test
    void savePresentationCommand_doesNotThrow(){
        Presentation presentation = mock(Presentation.class);
        Frame frame = new Frame();

        Assertions.assertDoesNotThrow(()->{new SavePresentationCommand(presentation, frame);});
    }

    @Test
    void execute(){
        Presentation presentation = mock(Presentation.class);
        Frame frame = new Frame();
        Command command = new SavePresentationCommand(presentation, frame);

        Assertions.assertDoesNotThrow(()->{command.execute();});
    }
}
