package com.ubs.exercise.chatwall.model;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class SimpleUserTest {
    private static final String NAME = "name";
    User user;

    @Before
    public void init() {
        user = new SimpleUser(NAME);
    }

    @Test
    public void subscriptionsAreEmptyAsDefault() {
        assertTrue(user.getSubscriptions().isEmpty());
    }

    @Test
    public void whenUserIsSubscribedToOtherThenItIsStored() {
        User other = new SimpleUser("other");

        user.subscribe(other);

        assertEquals(1, user.getSubscriptions().size());
        assertTrue(user.getSubscriptions().contains(other));
    }

    @Test
    public void whenUserIsSubscribedForItselfItIsNotStored() {
        user.subscribe(user);

        assertTrue(user.getSubscriptions().isEmpty());
    }

    @Test
    public void whenUserIsSubscribedToMultipleOtherThenAllIsStored() {
        User other1 = new SimpleUser("other1");
        User other2 = new SimpleUser("other2");

        user.subscribe(other1);
        user.subscribe(other2);

        assertEquals(2, user.getSubscriptions().size());
        assertTrue(user.getSubscriptions().contains(other1));
        assertTrue(user.getSubscriptions().contains(other2));
    }

    @Test
    public void whenUserIsSubscribedToSameOtherMultipleTimesThenStoredOnce() {
        User other = new SimpleUser("other1");

        user.subscribe(other);
        user.subscribe(other);

        assertEquals(1, user.getSubscriptions().size());
        assertTrue(user.getSubscriptions().contains(other));
    }
}
