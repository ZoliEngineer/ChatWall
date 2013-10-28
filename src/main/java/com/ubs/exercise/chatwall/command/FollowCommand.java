package com.ubs.exercise.chatwall.command;

import java.util.Collections;
import java.util.List;

import com.google.common.base.Strings;
import com.ubs.exercise.chatwall.model.User;
import com.ubs.exercise.chatwall.storage.Storage;

public class FollowCommand implements Command {
    private static final String FOLLOW_COMMAND_PART = "follows";
    private final Storage storage;

    public FollowCommand(Storage storage) {
        this.storage = storage;
    }

    @Override
    public List<String> execute(String commandString) {
        String[] splitedCommand = commandString.split(FOLLOW_COMMAND_PART);
        String userName = splitedCommand[0].trim();
        String userNameToFollow = splitedCommand[1].trim();

        follow(userName, userNameToFollow);

        return Collections.emptyList();
    }

    @Override
    public boolean matches(String commandLine) {
        if (Strings.isNullOrEmpty(commandLine)) {
            return false;
        } else {
            return commandLine.contains(FOLLOW_COMMAND_PART);
        }
    }

    private void follow(String userName, String userNameToFollow) {
        User currentUser = storage.getUser(userName);
        User userToFollow = storage.getUser(userNameToFollow);
        currentUser.subscribe(userToFollow);
    }

}