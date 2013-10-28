package com.ubs.exercise.chatwall;

import java.util.List;

import com.google.common.collect.Lists;
import com.ubs.exercise.chatwall.command.Command;
import com.ubs.exercise.chatwall.command.CommandDispatcher;
import com.ubs.exercise.chatwall.command.ExitCommand;
import com.ubs.exercise.chatwall.command.FallbackCommand;
import com.ubs.exercise.chatwall.command.FollowCommand;
import com.ubs.exercise.chatwall.command.InOrderCommandDispatcher;
import com.ubs.exercise.chatwall.command.PublishCommand;
import com.ubs.exercise.chatwall.command.ReadCommand;
import com.ubs.exercise.chatwall.command.WallCommand;
import com.ubs.exercise.chatwall.storage.MemoryChatStorage;
import com.ubs.exercise.chatwall.storage.MemoryUserStorage;
import com.ubs.exercise.chatwall.storage.Storage;
import com.ubs.exercise.chatwall.storage.StorageComposition;

public class DependencyConfiguration {

    private final Storage storage = new StorageComposition(new MemoryUserStorage(), new MemoryChatStorage());
    private final Command fallbackCommand = new FallbackCommand();
    private final List<Command> knownCommandsInOrder = Lists.newArrayList(//
            new PublishCommand(storage),//
            new FollowCommand(storage),//
            new WallCommand(storage),//
            new ExitCommand(),//
            new ReadCommand(storage)//
            );

    private final CommandDispatcher commandDispatcher = new InOrderCommandDispatcher(knownCommandsInOrder, fallbackCommand);

    public CommandDispatcher getCommandDispatcher() {
        return commandDispatcher;
    }
}
