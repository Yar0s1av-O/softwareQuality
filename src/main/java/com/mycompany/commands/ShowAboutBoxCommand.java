package com.mycompany.commands;
import com.mycompany.AboutBox;

import java.awt.Frame;

public class ShowAboutBoxCommand implements Command {
    private final Frame parent;

    public ShowAboutBoxCommand(Frame parent) {
        this.parent = parent;
    }

    @Override
    public void execute() {
        AboutBox.show(parent);
    }
}
