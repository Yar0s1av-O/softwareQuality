package com.mycompany.commands;

import com.mycompany.Presentation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.mockito.Mockito.*;
public class OpenPresentationCommandTest
{
    @Test
    void openPresentationCommand_doesNotThrow(){
        Presentation presentation = mock(Presentation.class);
        Frame frame = new Frame();

        Assertions.assertDoesNotThrow(()->{new OpenPresentationCommand(presentation, frame);});
    }
}
