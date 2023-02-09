package com.tuimm.leaarningpath.cli;

import java.io.PrintStream;
import java.util.Scanner;

public interface Command {
    void execute(PrintStream outputStream, Scanner scanner);
}
