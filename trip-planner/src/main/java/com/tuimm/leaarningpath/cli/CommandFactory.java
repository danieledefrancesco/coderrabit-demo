package com.tuimm.leaarningpath.cli;

public interface CommandFactory {
    String getCommandRegex();
    String getCommandExample();
    Command createCommand(String commandLine);
}
