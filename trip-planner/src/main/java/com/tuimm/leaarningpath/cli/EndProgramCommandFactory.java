package com.tuimm.leaarningpath.cli;

public class EndProgramCommandFactory implements CommandFactory{
    @Override
    public String getCommandRegex() {
        return "^exit$";
    }

    @Override
    public String getCommandExample() {
        return "exit";
    }

    @Override
    public Command createCommand(String commandLine) {
        return EndProgramCommand.getInstance();
    }
}
