package com.ubs.exercise.chatwall.command;

import java.util.Collections;
import java.util.List;

public class FallbackCommand implements Command {

    @Override
    public List<String> execute(String input) {
        System.out.println("Unknown command");
        return Collections.emptyList();
    }

    @Override
    public boolean matches(String commandLine) {
        return true;
    }

}
