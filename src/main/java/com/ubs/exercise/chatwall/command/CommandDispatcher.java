package com.ubs.exercise.chatwall.command;

public interface CommandDispatcher {

    /**
     * Returns the {@Command} from the {@Command} list given in the constructor which matches the input
     * {@Command} string
     * 
     * @param input
     *            Command string from the input
     * @return {@Command} implementation, which is eligible to execute the command string. Returns the configured
     *         fallback command if no matching command found
     */
    Command dispatch(String input);
}
