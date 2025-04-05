package com.mycompany.commands;

import com.mycompany.Presentation;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class PrevSlideCommandTest {

    @Test
    void testExecuteShouldGoToPreviousSlide() {
        Presentation presentation = mock(Presentation.class);
        PrevSlideCommand command = new PrevSlideCommand(presentation);

        command.execute();

        verify(presentation, times(1)).prevSlide();
    }
}
