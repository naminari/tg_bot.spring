package com.java;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@RestController
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


    @PostMapping("/sendMessage")
    public JSONObject sendMessage(@RequestBody JSONObject json){
        String text_message = json.getString("arg1");
        Long chat_id = Long.parseLong(json.getString("arg2"));

        bot.sendMessage(chat_id, text_message);

        JSONObject obj = new JSONObject();
        obj.put("resp", "success");
        return obj;
    }

}
