package com.ubs.exercise.chatwall.command;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.collect.Lists;

@RunWith(MockitoJUnitRunner.class)
public class InOrderCommandDispatcherTest {

    @Mock
    private Command  command1;

    @Mock
    private Command command2;

    @Mock
    private Command fallbackCommand;

    InOrderCommandDispatcher commandDispatcher;

    @Before
    public void initTest() {
        commandDispatcher = new InOrderCommandDispatcher(Lists.newArrayList(command1, command2), fallbackCommand);
    }

    @Test
    public void whenDispatchCalledThenCommandsAreCheckedInOrder() {
        String input = "";

        commandDispatcher.dispatch(input);

        InOrder inOrder = Mockito.inOrder(command1, command2);
        inOrder.verify(command1).matches(input);
        inOrder.verify(command2).matches(input);

    }

    @Test
    public void whenCommandMatchesThenItIsReturnedAndNoMoreCommandIsChecked() {
        when(command1.matches(anyString())).thenReturn(true);
        when(command2.matches(anyString())).thenReturn(false);

        String input = "";

        Command command = commandDispatcher.dispatch(input);

        assertEquals(command1, command);
        verify(command1).matches(anyString());
        verify(command2, never()).matches(anyString());

    }

    @Test
    public void whenNoCommandMatchesThenReturnsFallbackCommand() {
        when(command1.matches(anyString())).thenReturn(false);
        when(command2.matches(anyString())).thenReturn(false);

        String input = "";

        Command command = commandDispatcher.dispatch(input);

        assertEquals(fallbackCommand, command);
        verify(command1).matches(anyString());
        verify(command2).matches(anyString());

    }


}
