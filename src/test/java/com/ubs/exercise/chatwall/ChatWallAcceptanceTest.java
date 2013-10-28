package com.ubs.exercise.chatwall;

import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class ChatWallAcceptanceTest {
    private final ChatWall instance = new ChatWall();

    @Test
    public void verifyGivenScenarioWithoutTiming() throws InterruptedException {
        List<String> input = new ArrayList<>();
        input.add("Alice -> I love the weather today");
        input.add("Bob -> Oh, we lost!");
        input.add("Bob -> at least it's sunny");
        input.add("Alice");
        input.add("Bob");
        input.add("Charlie -> I'm in New York today! Anyone wants to have a coffee?");
        input.add("Charlie follows Alice");
        input.add("Charlie wall");
        input.add("Charlie follows Bob");
        input.add("Charlie wall");

        List<String> expectedOutput = new ArrayList<>();
        expectedOutput.add("I love the weather today (0 second ago)");
        expectedOutput.add("Oh, we lost! (0 second ago)");
        expectedOutput.add("at least it's sunny (0 second ago)");
        expectedOutput.add("Charlie - I'm in New York today! Anyone wants to have a coffee? (0 second ago)");
        expectedOutput.add("Alice - I love the weather today (0 second ago)");
        expectedOutput.add("Charlie - I'm in New York today! Anyone wants to have a coffee? (0 second ago)");
        expectedOutput.add("Bob - at least it's sunny (0 second ago)");
        expectedOutput.add("Bob - Oh, we lost! (0 second ago)");
        expectedOutput.add("Alice - I love the weather today (0 second ago)");


        List<String> actualOutput = new ArrayList<>();
        for (String command : input) {
            actualOutput.addAll(instance.executeCommand(command));
            Thread.sleep(10);
        }

        assertArrayEquals(expectedOutput.toArray(), actualOutput.toArray());
    }

}
