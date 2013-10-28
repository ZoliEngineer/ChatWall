package com.ubs.exercise.chatwall.storage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertSame;

import org.junit.Before;
import org.junit.Test;

import com.ubs.exercise.chatwall.model.User;

public class MemoryUserStorageTest {
    UserStorage userStorage;

    @Before
    public void init() {
        userStorage = new MemoryUserStorage();
    }

    @Test
    public void whenUserNotExistsThenCreatesOneAndReturns() {
        final String NAME = "name";

        User user = userStorage.get(NAME);

        assertEquals(NAME, user.getName());
    }

    @Test
    public void whenUserExistsThenReturnsTheSameInstance() {
        final String NAME = "name";

        User user1 = userStorage.get(NAME);
        User user2 = userStorage.get(NAME);

        assertEquals(NAME, user1.getName());
        assertEquals(NAME, user2.getName());
        assertSame(user1, user2);
    }

    @Test
    public void whenTwoUserRequestedThenReturnsDifferentInstances() {
        final String NAME_1 = "name1";
        final String NAME_2 = "name2";

        User user1 = userStorage.get(NAME_1);
        User user2 = userStorage.get(NAME_2);

        assertEquals(NAME_1, user1.getName());
        assertEquals(NAME_2, user2.getName());
        assertNotEquals(user1, user2);
    }
}
