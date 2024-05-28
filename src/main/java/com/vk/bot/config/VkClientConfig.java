package com.vk.bot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "vk-client", ignoreUnknownFields = false)
public record VkClientConfig(
        String vkApiKey,
        String vkApiVersion,
        String vkApiUrl,
        String vkUnreadMessageUrl,
        String vkSendMessageUrl,
        Integer messageCount) {
}