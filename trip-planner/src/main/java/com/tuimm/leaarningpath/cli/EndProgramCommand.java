package com.tuimm.leaarningpath.cli;

import java.io.PrintStream;
import java.util.Scanner;

public class EndProgramCommand implements Command {
    private static final EndProgramCommand instance = new EndProgramCommand();

    public static EndProgramCommand getInstance() {
        return instance;
    }

    private EndProgramCommand() {
    }

    @Override
    public void execute(PrintStream outputStream, Scanner scanner) {
        outputStream.println("exiting");
    }
}
