package com.vk.bot;


import com.vk.bot.controller.BotController;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BotScheduler {

    private final BotController botController;

    @Scheduled(fixedDelayString = "#{@scheduler.delay().toMillis()}")
    public void update() {
        botController.receiveMessages();
    }
}
