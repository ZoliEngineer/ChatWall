package com.ubs.exercise.chatwall.command;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class FallbackCommandTest {
    private final Command command = new FallbackCommand();

    @Test
    public void commandAlwaysMatches() {
        assertTrue(command.matches(null));
        assertTrue(command.matches(""));
        assertTrue(command.matches("any input"));
    }
}
