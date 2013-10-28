package com.ubs.exercise.chatwall.storage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ubs.exercise.chatwall.model.ChatLine;
import com.ubs.exercise.chatwall.model.User;


public class MemoryChatStorage implements ChatStorage{
    private final Map<User, List<ChatLine>> chatLineRepository = new HashMap<>();

    @Override
    public void addLine(User user, ChatLine line) {
        List<ChatLine> timeLine = chatLineRepository.get(user);
        if(timeLine == null){
            timeLine = initTimeLine(user);
        }
        timeLine.add(line);
    }

    @Override
    public List<ChatLine> getLines(User user) {
        List<ChatLine> lines = chatLineRepository.get(user);
        if (lines == null) {
            lines = Collections.emptyList();
        }
        return Collections.unmodifiableList(lines);
    }

    private List<ChatLine> initTimeLine(User user) {
        List<ChatLine> timeLine = new ArrayList<>();
        chatLineRepository.put(user, timeLine);
        return timeLine;
    }
}
