package com.ubs.exercise.chatwall.command;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ExitCommandTest {
    private final Command command = new ExitCommand();

    @Test
    public void commandDoesntMatchEmptyOrInvalidString() {
        assertFalse(command.matches(null));
        assertFalse(command.matches(""));
        assertFalse(command.matches("any input"));
    }

    @Test
    public void commandMatchesExitString() {
        assertTrue(command.matches("exit"));
    }
}
