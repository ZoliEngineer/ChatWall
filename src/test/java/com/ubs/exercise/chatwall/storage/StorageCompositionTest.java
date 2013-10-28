package com.ubs.exercise.chatwall.storage;

import static org.junit.Assert.assertEquals;
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

@RunWith(MockitoJUnitRunner.class)
public class StorageCompositionTest {
    @Mock
    private UserStorage userStorage;

    @Mock
    private ChatStorage chatStorage;

    private Storage storage;


    @Before
    public void init() {
        storage = new StorageComposition(userStorage, chatStorage);
    }

    @Test
    public void whenGetUserCalledUserStorageIsQueried() {
        String name = "name";
        User user = mock(User.class);
        when(userStorage.get(eq(name))).thenReturn(user);

        assertEquals(user, storage.getUser(name));
    }

    @Test
    public void whenGetMessagesForCalledThenChatStorageIsQueried(){
        String message = "message";
        User user = mock(User.class);
        ChatLine line = new ChatLine(user, message, 0L);
        when(chatStorage.getLines(eq(user))).thenReturn(Lists.newArrayList(line));

        List<ChatLine> linesOfUser=    storage.getMessagesOf(user);

        assertEquals(1,linesOfUser.size());
        assertEquals(line, linesOfUser.get(0));
    }

    @Test
    public void whenPublishMessageCalledThenMessageSavedInChatStorage(){
        String message = "message";
        User user = mock(User.class);
        ChatLine line = new ChatLine(user, message, 0L);

        storage.publishMessage(user, line);

        verify(chatStorage).addLine(same(user), same(line));
    }
}
