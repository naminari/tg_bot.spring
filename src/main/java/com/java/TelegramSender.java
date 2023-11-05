package com.java;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@SpringBootApplication
public class TelegramSender {
    public static void main(String args[]) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Bot.class);
        Bot bot = context.getBean(Bot.class);
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(bot);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

        String messageText1 = "Привет лошок";
        bot.sendMessage(bot.getMap().get("naminari"), messageText1);

    }
}