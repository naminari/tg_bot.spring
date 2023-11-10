package com.java.bot.controller;

import com.java.bot.repository.Bot;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Slf4j
@RestController
@SuppressWarnings("unused")
@RequestMapping("/api/bot")
public class BotController {

    private final Bot bot;

    public BotController(Bot bot){
        this.bot = bot;
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(bot);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/userExist")
    public boolean userExist(@RequestBody JSONObject json){
        log.debug("Method - userExist was called");
        String name = json.getString("tg_name");
        boolean result = false;
        if (bot.getService().userExist(name) == 1){
            result = true;
        }
        return result;
    }


    @PostMapping("/sendMessage")
    public JSONObject sendMessage(@RequestBody JSONObject json){
        String text_message = json.getString("arg1");
        long chat_id = Long.parseLong(json.getString("arg2"));

        bot.sendMessage(chat_id, text_message);

        JSONObject obj = new JSONObject();
        obj.put("resp", "success");
        return obj;
    }

}
