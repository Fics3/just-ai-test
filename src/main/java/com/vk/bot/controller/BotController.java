package com.vk.bot.controller;

import com.vk.bot.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@Log4j2
public class BotController {

    private final MessageService messageService;

    public void receiveMessages() {
        messageService.processMessages();
    }
}
