package com.ubs.exercise.chatwall.model;

import java.util.Set;

public interface User {
    String getName();

    /**
     * Connects the given {@User} instance to the current {@User} instance. This is a one way connection.
     *
     * @param user
     */
    void subscribe(User user);

    /**
     * Returns all the {@User} instances connected to the current {@User} instance.
     * 
     * @return
     */
    Set<User> getSubscriptions();
}
