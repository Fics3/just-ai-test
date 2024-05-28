package com.vk.bot.DTO;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.List;

public record VkUnreadConversationsResponse(
        Response response
) {
    public record Response(
            List<Item> items
    ) {
        @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
        public record Item(
                VkLastMessage lastMessage
        ) {
        }
    }

}
