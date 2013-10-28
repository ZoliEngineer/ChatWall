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

import com.google.common.collect.Lists;
import com.ubs.exercise.chatwall.model.ChatLine;
import com.ubs.exercise.chatwall.model.User;
import com.ubs.exercise.chatwall.storage.Storage;

@RunWith(MockitoJUnitRunner.class)
public class ReadCommandTest {
    private static final String USER = "user";
    private static final String PROPER_COMMAND = USER;

    @Mock
    private Storage storage;

    private Command command;

    @Before
    public void initTest() {
        command = new ReadCommand(storage);
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
    public void whenCommandExecutedThenReturnsAllChatLinesOfUser() {
        User user = mock(User.class);
        String message = "message";
        ChatLine chatLine = new ChatLine(user, message, System.currentTimeMillis());

        when(storage.getUser(eq(USER))).thenReturn(user);
        when(storage.getMessagesOf(eq(user))).thenReturn(Lists.newArrayList(chatLine));

        List<String> output = command.execute(PROPER_COMMAND);

        verify(storage).getUser(eq(USER));
        verify(storage).getMessagesOf(same(user));
        assertTrue(output.get(0).contains(message));
        assertFalse(output.isEmpty());
    }

}
