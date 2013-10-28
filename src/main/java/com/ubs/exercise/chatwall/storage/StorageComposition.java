package com.ubs.exercise.chatwall.storage;


import java.util.List;

import com.ubs.exercise.chatwall.model.ChatLine;
import com.ubs.exercise.chatwall.model.User;
public class StorageComposition implements Storage{
    private final UserStorage userStorage;
    private final ChatStorage chatStorage;

    public StorageComposition(UserStorage userStorage, ChatStorage chatStorage){
        this.chatStorage = chatStorage;
        this.userStorage = userStorage;
    }

    @Override
    public User getUser(String name) {
        return userStorage.get(name);
    }

    @Override
    public List<ChatLine> getMessagesOf(User user) {
        return chatStorage.getLines(user);
    }

    @Override
    public void publishMessage(User user, ChatLine chatLine) {
        chatStorage.addLine(user, chatLine);
    }
}
