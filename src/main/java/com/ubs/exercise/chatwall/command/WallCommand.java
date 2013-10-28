package com.ubs.exercise.chatwall.command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import com.google.common.base.Strings;
import com.ubs.exercise.chatwall.model.ChatLine;
import com.ubs.exercise.chatwall.model.User;
import com.ubs.exercise.chatwall.storage.Storage;
import com.ubs.exercise.chatwall.util.ChatLineFormatter;

public class WallCommand implements Command {
    private static final String WALL_COMMAND_PART = "wall";
    private final Storage storage;

    public WallCommand(Storage storage){
        this.storage = storage;
    }

    @Override
    public List<String> execute(String commandString) {
        String userName = commandString.split(WALL_COMMAND_PART)[0].trim();

        return readWall(userName);

    }

    @Override
    public boolean matches(String commandLine) {
        if (Strings.isNullOrEmpty(commandLine)) {
            return false;
        } else {
            return commandLine.contains(WALL_COMMAND_PART);
        }
    }

    private List<String> readWall(String userName) {
        User user = storage.getUser(userName);
        Set<User> subscriptions = user.getSubscriptions();

        List<ChatLine> allLines = new ArrayList<>();
        for(User subscription : subscriptions){
            allLines.addAll(storage.getMessagesOf(subscription));
        }
        allLines.addAll(storage.getMessagesOf(user));

        Collections.sort(allLines);
        return createOutput(allLines);
    }

    private List<String> createOutput(List<ChatLine> timeLine) {
        List<String> output = new ArrayList<>(timeLine.size());
        for(ChatLine line : timeLine)          {
            output.add(ChatLineFormatter.getFormattedLineWithUser(line, System.currentTimeMillis()));
        }
        return output;
    }
}