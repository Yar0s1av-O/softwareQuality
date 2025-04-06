package com.mycompany.commands;

import com.mycompany.Presentation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.mockito.Mockito.mock;

public class ShowAboutBoxCommandTest
{
    @Test
    void ShowAboutBoxCommand_doesNotThrow(){
        Frame frame = mock(Frame.class);

        Assertions.assertDoesNotThrow(()->{new ShowAboutBoxCommand(frame);});
    }
}
