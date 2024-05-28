package com.vk.bot.service;

import com.vk.bot.BotScheduler;
import com.vk.bot.client.VkClient;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.mockito.Mockito.verifyNoInteractions;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MessageServiceTest {

    @MockBean
    private BotScheduler botScheduler;

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private VkClient vkClient;

    @BeforeAll
    static void beforeAll() {
        System.setProperty("VK_API_KEY", "111");
        System.setProperty("VK_API_VERSION", "111");
    }

    @Test
    @DisplayName("One message response - should send message back")
    public void testProcessMessage_oneChatResponse() {
        webTestClient.get()
                .uri("/method/messages.getConversations?filter=unread&count=1&access_token=your_access_token&v=your_vk_api_version")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .json("{\"response\": {\"items\": [{\"last_message\": {\"peer_id\": 123, \"text\": \"Hello\"}}]}}");

        webTestClient.post()
                .uri("/method/messages.send?access_token=your_access_token&peer_id=123&random_id=456&message=Response message&v=your_vk_api_version")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .json("{\"response\": {\"message_id\": 123}}");
    }

    @Test
    @DisplayName("Invalid response from VK API - should not send any message")
    public void testProcessMessage_invalidResponse() {
        webTestClient.get()
                .uri("/method/messages.getConversationsNullResponse?filter=unread&count=1&access_token=your_access_token&v=your_vk_api_version")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .isEmpty();

        // Verify that sendMessageBack method is never called
        verifyNoInteractions(vkClient);
    }
}
