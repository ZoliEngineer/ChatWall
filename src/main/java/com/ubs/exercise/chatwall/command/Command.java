package com.ubs.exercise.chatwall.command;

import java.util.List;

public interface Command {

    /**
     * Executes the input command string
     * 
     * @param commandLine
     *            Command string to execute
     * @return the result of the command
     */
    List<String>  execute(String commandLine);

    /**
     * Decides if the given command is eligible to execute the given string
     * 
     * @param input
     *            command string which is able to be executed with this command
     * @return
     */
    boolean matches(String input);

}
