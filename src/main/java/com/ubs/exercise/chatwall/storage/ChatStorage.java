package com.ubs.exercise.chatwall.storage;

import java.util.List;

import com.ubs.exercise.chatwall.model.ChatLine;
import com.ubs.exercise.chatwall.model.User;

public interface ChatStorage {
    /**
     * Saves the given {@ChatLine} line for the given {@User}
     * 
     * @param user
     * @param line
     */
    void addLine(User user, ChatLine line);

    /**
     * Returns all the previously saved {@ChatLine} for the given {@User}
     *
     * @param user
     * @return
     */
    List<ChatLine> getLines(User user);
}
