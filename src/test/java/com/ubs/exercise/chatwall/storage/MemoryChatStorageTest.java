package com.ubs.exercise.chatwall.storage;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;

import com.ubs.exercise.chatwall.model.ChatLine;
import com.ubs.exercise.chatwall.model.User;

public class MemoryChatStorageTest {
    private ChatStorage chatStorage;

    @Before
    public void init() {
        chatStorage = new MemoryChatStorage();
    }

    @Test
    public void whenGetWallOfUnknownUserThanReturnsEmptyList() {
        User user = mock(User.class);
        assertTrue(chatStorage.getLines(user).isEmpty());
    }

    @Test
    public void whenLineIsSavedThenItCanBeQueriedByUser() {
        String message = "message";
        User user = mock(User.class);
        ChatLine line = new ChatLine(user, message, 0L);

        chatStorage.addLine(user, line);

        assertEquals(1, chatStorage.getLines(user).size());
        assertTrue(chatStorage.getLines(user).contains(line));
    }

    @Test
    public void whenMultipleLineIsSavedThenItCanBeQueriedByUser() {
        String message1 = "message1";
        String message2 = "message2";
        User user = mock(User.class);
        ChatLine line1 = new ChatLine(user, message1, 0L);
        ChatLine line2 = new ChatLine(user, message2, 0L);

        chatStorage.addLine(user, line1);
        chatStorage.addLine(user, line2);

        assertEquals(2, chatStorage.getLines(user).size());
        assertEquals(line1, chatStorage.getLines(user).get(0));
        assertEquals(line2, chatStorage.getLines(user).get(1));
    }

    @Test
    public void whenMultipleLineIsSavedByDifferentUsersThenItCanBeQueriedByUser() {
        String message1 = "message1";
        String message2 = "message2";
        User user1 = mock(User.class);
        User user2 = mock(User.class);
        ChatLine line1 = new ChatLine(user1, message1, 0L);
        ChatLine line2 = new ChatLine(user2, message2, 0L);

        chatStorage.addLine(user1, line1);
        chatStorage.addLine(user2, line2);

        assertEquals(1, chatStorage.getLines(user1).size());
        assertEquals(1, chatStorage.getLines(user2).size());
        assertTrue(chatStorage.getLines(user1).contains(line1));
        assertTrue(chatStorage.getLines(user2).contains(line2));
        assertFalse(chatStorage.getLines(user1).contains(line2));
        assertFalse(chatStorage.getLines(user2).contains(line1));
    }
}
