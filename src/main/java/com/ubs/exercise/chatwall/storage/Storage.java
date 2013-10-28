package com.ubs.exercise.chatwall.storage;


import java.util.List;

import com.ubs.exercise.chatwall.model.ChatLine;
import com.ubs.exercise.chatwall.model.User;

public interface Storage {

    /**
     * Returns the {@User} instance identified by the name paramter string
     * 
     * @param name
     * @return
     */
    User getUser(String name);

    /**
     * Returns all the previously saved {@ChatLine} for the given {@User}
     *
     * @param user
     * @return
     */
    List<ChatLine> getMessagesOf(User user);

    /**
     * Saves the given {@ChatLine} line for the given {@User}
     *
     * @param user
     * @param line
     */
    void publishMessage(User user, ChatLine chatLine);
}
