package com.tuimm.leaarningpath.cli;

import java.io.InputStream;
import java.io.PrintStream;

public class EndProgramCommand implements Command {
    private static final EndProgramCommand instance = new EndProgramCommand();

    public static EndProgramCommand getInstance() {
        return instance;
    }

    private EndProgramCommand() {
    }

    @Override
    public void execute(PrintStream outputStream, InputStream inputStream) {
        outputStream.println("exiting");
    }
}
