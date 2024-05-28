package com.vk.bot.client;


import com.vk.bot.DTO.VkUnreadConversationsResponse;
import com.vk.bot.config.VkClientConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@Log4j2
public class VkClient {

    private final VkClientConfig vkClientConfig;
    private final WebClient vkWebClient;

    public Mono<VkUnreadConversationsResponse> getUnreadConversations() {
        String conversationsUrl = String.format(
                vkClientConfig.vkUnreadMessageUrl(),
                vkClientConfig.messageCount(),
                vkClientConfig.vkApiKey(),
                vkClientConfig.vkApiVersion()
        );

        return vkWebClient.get()
                .uri(conversationsUrl)
                .retrieve()
                .bodyToMono(VkUnreadConversationsResponse.class)
                .onErrorResume(WebClientResponseException.class, e -> {
                    log.error("Error during VK API request: {}", e.getResponseBodyAsString(), e);
                    return Mono.empty();
                });
    }

    public Mono<Void> sendVkMessage(Integer peerId, Integer randomId, String message) {
        String sendUrl = String.format(
                vkClientConfig.vkSendMessageUrl(),
                vkClientConfig.vkApiKey(),
                peerId,
                randomId,
                message,
                vkClientConfig.vkApiVersion()
        );

        return vkWebClient.post()
                .uri(sendUrl)
                .retrieve()
                .bodyToMono(Void.class);
    }


}
