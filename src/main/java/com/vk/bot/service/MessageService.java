package com.vk.bot.service;

import com.vk.bot.DTO.VkUnreadConversationsResponse;
import com.vk.bot.client.VkClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final VkClient vkClient;

    public void processMessages() {
        var vkUnreadConversationResponse = vkClient.getUnreadConversations().block();
        if (isInvalidResponse(vkUnreadConversationResponse)) {
            return;
        }
        for (var item : vkUnreadConversationResponse.response().items()) {
            sendMessageBack(item.lastMessage().peerId(), generateUniqueRandomId(), item.lastMessage().text());
        }
    }

    private void sendMessageBack(Integer peerId, Integer randomId, String message) {
        var resultMessage = "Вы написали: " + message;
        vkClient.sendVkMessage(peerId, randomId, resultMessage).block();
    }

    private boolean isInvalidResponse(VkUnreadConversationsResponse vkUnreadConversationsResponse) {
        return vkUnreadConversationsResponse == null || vkUnreadConversationsResponse.response() == null;
    }

    private Integer generateUniqueRandomId() {
        return UUID.randomUUID().hashCode();
    }
}
