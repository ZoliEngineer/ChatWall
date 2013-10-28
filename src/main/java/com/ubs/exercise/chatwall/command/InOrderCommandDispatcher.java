package com.ubs.exercise.chatwall.command;


import java.util.List;

public class InOrderCommandDispatcher implements CommandDispatcher {
    private final List<Command> commands;
    private final Command fallbackCommand;

    public InOrderCommandDispatcher(List<Command> commands, Command fallbackCommand) {
        this.commands = commands;
        this.fallbackCommand = fallbackCommand;
    }

    @Override
    public Command dispatch(String input) {
        for (Command command : commands) {
            if (command.matches(input)) {
                return command;
            }
        }
        return fallbackCommand;
    }
}
