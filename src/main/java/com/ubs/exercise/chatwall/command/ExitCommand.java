package com.ubs.exercise.chatwall.command;

import java.util.Collections;
import java.util.List;

public class ExitCommand implements Command {
    private static final String PATTERN = "exit";

    @Override
    public List<String> execute(String input) {
        System.out.println("Exiting application");
        System.exit(0);
        return Collections.emptyList();
    }


    @Override
    public boolean matches(String commandLine) {
        return PATTERN.equals(commandLine);
    }
}
