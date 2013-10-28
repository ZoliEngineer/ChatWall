package com.ubs.exercise.chatwall.command;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.same;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.ubs.exercise.chatwall.model.User;
import com.ubs.exercise.chatwall.storage.Storage;

@RunWith(MockitoJUnitRunner.class)
public class FollowCommandTest {
    private static final String USER_1 = "user1";
    private static final String USER_2 = "user2";
    private static final String PROPER_COMMAND = USER_1 + " follows " + USER_2;

    @Mock
    private Storage storage;

    private Command command;

    @Before
    public void initTest() {
        command = new FollowCommand(storage);
    }

    @Test
    public void commandDoesntMatchNull() {
        assertFalse(command.matches(null));
    }

    @Test
    public void commandDoesntMatchEmpty() {
        assertFalse(command.matches(""));
    }

    @Test
    public void commandMatchesProperCommandString() {
        assertTrue(command.matches(PROPER_COMMAND));
    }

    @Test
    public void whenCommandExecutedThenStoresSubscriptionsForUser() {
        User user1 = mock(User.class);
        User user2 = mock(User.class);
        when(storage.getUser(eq(USER_1))).thenReturn(user1);
        when(storage.getUser(eq(USER_2))).thenReturn(user2);

        List<String> output = command.execute(PROPER_COMMAND);

        verify(storage).getUser(eq(USER_1));
        verify(storage).getUser(eq(USER_2));
        verify(user1).subscribe(same(user2));
        assertTrue(output.isEmpty());
    }
}
