package com.ubs.exercise.chatwall;

import java.util.List;

import com.ubs.exercise.chatwall.command.Command;
import com.ubs.exercise.chatwall.command.CommandDispatcher;

/**
 *
 * @author Zoltan Juhasz
 *
 */
public class ChatWall {
    private final CommandDispatcher commandDispatcher;

    public ChatWall() {
        DependencyConfiguration config = new DependencyConfiguration();
        this.commandDispatcher = config.getCommandDispatcher();
    }

    public List<String> executeCommand(String commandString) {
        Command command = commandDispatcher.dispatch(commandString);
        List<String> result = command.execute(commandString);
        return result;
    }
}
