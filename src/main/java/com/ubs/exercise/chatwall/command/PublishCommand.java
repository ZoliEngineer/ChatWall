package com.ubs.exercise.chatwall.command;

import java.util.Collections;
import java.util.List;

import com.google.common.base.Strings;
import com.ubs.exercise.chatwall.model.ChatLine;
import com.ubs.exercise.chatwall.model.User;
import com.ubs.exercise.chatwall.storage.Storage;

public class PublishCommand implements Command {
    private static final String PUBLISH_COMMAND_PART = "->";

    private final Storage storage;

    public PublishCommand(Storage storage){
        this.storage = storage;
    }

    @Override
    public List<String> execute(String commandString) {
        String[] splitedCommand = commandString.split(PUBLISH_COMMAN_PART);
        String userName = splitedCommand[0].trim();
        String message = splitedCommand[1].trim();

        publish(userName, message);

        return Collections.emptyList();
    }

    @Override
    public boolean matches(String commandLine) {
        if (Strings.isNullOrEmpty(commandLine)) {
            return false;
        } else {
            return commandLine.contains(PUBLISH_COMMAN_PART);
        }
    }

    private void publish(String userName, String message) {
        User user = storage.getUser(userName);
        ChatLine line = new ChatLine(user, message, System.currentTimeMillis());
        storage.publishMessage(user, line);
    }
}