package com.vk.bot.service;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/method")
public class TestVKApiController {

    @GetMapping(value = "/messages.getConversations", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getConversations() {
        return "{\"response\": {\"items\": [{\"last_message\": {\"peer_id\": 123, \"text\": \"Hello\"}}]}}";
    }

    @PostMapping(value = "/messages.send", produces = MediaType.APPLICATION_JSON_VALUE)
    public String sendMessage() {
        return "{\"response\": {\"message_id\": 123}}";
    }

    @GetMapping(value = "/messages.getConversationsNullResponse", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getConversationsNullResponse() {
        return null;
    }
}
