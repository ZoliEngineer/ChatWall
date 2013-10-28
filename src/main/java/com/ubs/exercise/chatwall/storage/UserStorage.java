package com.ubs.exercise.chatwall.storage;

import com.ubs.exercise.chatwall.model.User;

public interface UserStorage {
    /**
     * Returns the {@User} identified by the name paramter string. If no {@User} instance is associated with the
     * given name, then creates a new {@User} instance and returns it
     *
     * @param name
     * @return
     */
    public User get(String name);
}
