package com.vk.bot.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@RequiredArgsConstructor
public class WebClientConfig {

    private final VkClientConfig vkClientConfig;

    @Bean
    public WebClient vkWebClient() {
        return WebClient.builder()
                .baseUrl(vkClientConfig.vkApiUrl())
                .build();
    }
}
