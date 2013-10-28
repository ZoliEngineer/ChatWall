package com.ubs.exercise.chatwall.command;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Strings;
import com.ubs.exercise.chatwall.model.ChatLine;
import com.ubs.exercise.chatwall.model.User;
import com.ubs.exercise.chatwall.storage.Storage;
import com.ubs.exercise.chatwall.util.ChatLineFormatter;

public class ReadCommand implements Command {
    private final Storage storage;

    public ReadCommand(Storage storage){
        this.storage = storage;
    }

    @Override
    public List<String> execute(String commandString) {
        String userName = commandString.trim();

        return read(userName);
    }

    @Override
    public boolean matches(String commandLine) {
        if (Strings.isNullOrEmpty(commandLine)) {
            return false;
        } else {
            return true;
        }
    }

    private List<String> read(String userName) {
        User user = storage.getUser(userName);
        List<ChatLine> timeLine = storage.getMessagesOf(user);
        return createOutput(timeLine);
    }

    private List<String> createOutput(List<ChatLine> timeLine) {
        List<String> output = new ArrayList<>(timeLine.size());
        for(ChatLine line : timeLine)          {
            output.add(ChatLineFormatter.getFormattedLine(line, System.currentTimeMillis()));
        }
        return output;
    }
}

