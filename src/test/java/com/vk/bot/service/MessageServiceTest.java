package com.vk.bot.service;

import com.vk.bot.DTO.VkLastMessage;
import com.vk.bot.DTO.VkUnreadConversationsResponse;
import com.vk.bot.client.VkClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class MessageServiceTest {

    @Mock
    private VkClient vkClient;

    @InjectMocks
    private MessageService messageService;

    @BeforeEach
    void setUp() {
        initMocks(this);
    }

    @Test
    void testProcessMessages_noUnreadMessages() {
        // Arrange
        when(vkClient.getUnreadConversations()).thenReturn(Mono.just(new VkUnreadConversationsResponse(null)));

        // Act
        messageService.processMessages();

        // Assert
        verify(vkClient, never()).sendVkMessage(anyInt(), anyInt(), anyString());
    }

    @Test
    void testProcessMessages_withUnreadMessages() {
        // Arrange
        VkLastMessage lastMessage = new VkLastMessage("Hello!", 123);
        var item = new VkUnreadConversationsResponse.Response.Item(lastMessage);
        VkUnreadConversationsResponse response = new VkUnreadConversationsResponse(new VkUnreadConversationsResponse.Response(List.of(item)));

        when(vkClient.getUnreadConversations()).thenReturn(Mono.just(response));
        when(vkClient.sendVkMessage(anyInt(), anyInt(), anyString())).thenReturn(Mono.empty());

        // Act
        messageService.processMessages();

        // Assert
        verify(vkClient, times(1)).sendVkMessage(eq(123), anyInt(), eq("Вы написали: Hello!"));
    }

}
