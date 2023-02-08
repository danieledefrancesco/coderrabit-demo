package com.tuimm.leaarningpath.cli;

import java.io.InputStream;
import java.io.PrintStream;

public interface Command {
    void execute(PrintStream outputStream, InputStream inputStream);
}
