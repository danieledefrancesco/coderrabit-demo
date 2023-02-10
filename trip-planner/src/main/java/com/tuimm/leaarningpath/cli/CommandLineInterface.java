package com.tuimm.leaarningpath.cli;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Collection;
import java.util.Scanner;
import java.util.regex.Pattern;

@RequiredArgsConstructor
public class CommandLineInterface {
    @NonNull
    private final InputStream inputStream;
    @NonNull
    private final PrintStream outputStream;
    @NonNull
    private final Collection<CommandFactory> factories;


    public void run() {
        showExamples();
        Scanner scanner = new Scanner(inputStream);
        boolean exit = false;
        while (!exit) {
            try {
                exit = !processNextLine(scanner);
            }
            catch (Exception e) {
                outputStream.printf("An exception occurred: %s%n", e.getClass().getName());
                showExamples();
            }
        }
    }

    private boolean processNextLine(Scanner scanner) {
        String commandLine = scanner.nextLine();
        CommandFactory commandFactory = factories.stream()
                .filter(factory -> Pattern.matches(factory.getCommandRegex(), commandLine))
                .findFirst()
                .orElse(null);

        if (commandFactory == null) {
            outputStream.println("unrecognized command.");
            showExamples();
            return true;
        }

        Command command = commandFactory.createCommand(commandLine);
        command.execute(outputStream, scanner);
        return command != EndProgramCommand.getInstance();
    }

    private void showExamples() {
        outputStream.println("The valid commands are the following:");
        for (CommandFactory factory : factories) {
            outputStream.println(factory.getCommandExample());
        }
    }
}
