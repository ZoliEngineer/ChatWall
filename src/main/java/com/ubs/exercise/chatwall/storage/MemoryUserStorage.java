package com.ubs.exercise.chatwall.storage;

import java.util.HashMap;
import java.util.Map;

import com.ubs.exercise.chatwall.model.SimpleUser;
import com.ubs.exercise.chatwall.model.User;

public class MemoryUserStorage implements UserStorage {

    private final Map<String, User> userRepository = new HashMap<>();

    @Override
    public User get(String name) {
        User user = userRepository.get(name);
        if (user == null) {
            user = createUser(name);
        }
        return user;
    }

    private User createUser(String name) {
        User user = new SimpleUser(name);
        userRepository.put(name, user);
        return user;
    }
}
