package com.ubs.exercise.chatwall.command;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.argThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.ubs.exercise.chatwall.model.ChatLine;
import com.ubs.exercise.chatwall.model.User;
import com.ubs.exercise.chatwall.storage.Storage;

@RunWith(MockitoJUnitRunner.class)
public class PublishCommandTest {

    private static final String USER = "user";
    private static final String MESSAGE = "message";
    private static final String PROPER_COMMAND = USER
            + " -> " + MESSAGE;

    @Mock
    private Storage storage;

    private Command command;

    @Before
    public void initTest() {
        command = new PublishCommand(storage);
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
    public void whenCommandExecutedThenMessageIsSaved() {
        User user = mock(User.class);
        when(user.getName()).thenReturn(USER);
        when(storage.getUser(eq(USER))).thenReturn(user);

        List<String> output = command.execute(PROPER_COMMAND);

        verify(storage).getUser(eq(USER));
        verify(storage).publishMessage(eq(user), argThat(new ChatLineMatcher(USER, MESSAGE)));
        assertTrue(output.isEmpty());

    }

    private class ChatLineMatcher extends ArgumentMatcher<ChatLine> {
        private final String user;
        private final String message;

        public ChatLineMatcher(String user, String message) {
            this.user = user;
            this.message = message;
        }

        @Override
        public boolean matches(Object o) {
            if (o instanceof ChatLine) {
                ChatLine line = (ChatLine) o;
                return message.equals(line.getLine()) && user.equals(line.getUserName());
            } else {
                return false;
            }
        }

    }


}

