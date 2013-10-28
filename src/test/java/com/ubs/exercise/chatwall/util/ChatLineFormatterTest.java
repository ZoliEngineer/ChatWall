package com.ubs.exercise.chatwall.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.ubs.exercise.chatwall.model.ChatLine;
import com.ubs.exercise.chatwall.model.SimpleUser;

public class ChatLineFormatterTest {
    private static final String TEST_USER = "Charlie";
    private static final String TEST_CHAT_LINE = "Hello world!";

    @Test
    public void verifyChatLineFormat1Second() {
        int seconds = 1;

        String formattedString = ChatLineFormatter.getFormattedLineWithUser(new ChatLine(new SimpleUser(TEST_USER),
                TEST_CHAT_LINE, 0), seconds * 1000);

        assertEquals("Charlie - Hello world! (1 second ago)", formattedString);

    }

    @Test
    public void verifyChatLineFormat5SecondsWithUser() {
        int seconds = 5;

        String formattedString = ChatLineFormatter.getFormattedLineWithUser(new ChatLine(new SimpleUser(TEST_USER),
                TEST_CHAT_LINE, 0), seconds * 1000);

        assertEquals("Charlie - Hello world! (5 seconds ago)", formattedString);
    }

    @Test
    public void verifyChatLineFormat1MinuteWithUser() {
        int seconds = 60;

        String formattedString = ChatLineFormatter.getFormattedLineWithUser(new ChatLine(new SimpleUser(TEST_USER),
                TEST_CHAT_LINE, 0), seconds * 1000);

        assertEquals("Charlie - Hello world! (1 minute ago)", formattedString);

    }

    @Test
    public void verifyChatLineFormat5MinutesWithUser() {
        int seconds = 300;

        String formattedString = ChatLineFormatter.getFormattedLineWithUser(new ChatLine(new SimpleUser(TEST_USER),
                TEST_CHAT_LINE, 0), seconds * 1000);

        assertEquals("Charlie - Hello world! (5 minutes ago)", formattedString);

    }

    @Test
    public void verifyChatLineFormat1SecondWithOutUser() {
        int seconds = 1;

        String formattedString = ChatLineFormatter.getFormattedLine(new ChatLine(new SimpleUser(TEST_USER), TEST_CHAT_LINE, 0),
                seconds * 1000);

        assertEquals("Hello world! (1 second ago)", formattedString);

    }

    @Test
    public void verifyChatLineFormat5SecondsWithOutUser() {
        int seconds = 5;

        String formattedString = ChatLineFormatter.getFormattedLine(new ChatLine(new SimpleUser(TEST_USER), TEST_CHAT_LINE, 0),
                seconds * 1000);

        assertEquals("Hello world! (5 seconds ago)", formattedString);
    }

    @Test
    public void verifyChatLineFormat1MinuteWithOutUser() {
        int seconds = 60;

        String formattedString = ChatLineFormatter.getFormattedLine(new ChatLine(new SimpleUser(TEST_USER), TEST_CHAT_LINE, 0),
                seconds * 1000);

        assertEquals("Hello world! (1 minute ago)", formattedString);

    }

    @Test
    public void verifyChatLineFormat5MinutesWithOutUser() {
        int seconds = 300;

        String formattedString = ChatLineFormatter.getFormattedLine(new ChatLine(new SimpleUser(TEST_USER), TEST_CHAT_LINE, 0),
                seconds * 1000);

        assertEquals("Hello world! (5 minutes ago)", formattedString);

    }
}
