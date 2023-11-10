package com.java.bot.repository;


import com.java.bot.dto.User;
import com.java.bot.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.*;
import java.util.HashMap;

@Slf4j
public final class Bot extends TelegramLongPollingBot {

    private UserService service;



//    @Override
//    public void onUpdateReceived(Update update) {
//        if (update.hasMessage() && update.getMessage().hasText()) {
//            Long chatId = update.getMessage().getChatId();
//            String username = update.getMessage().getFrom().getUserName();
//            if (!userProcessedMap.containsKey(username)) {
//                userProcessedMap.put(username,chatId);
//                saveHashMapToFile(userProcessedMap);
//            }
//        }
//    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            Long chatId = update.getMessage().getChatId();
            String username = update.getMessage().getFrom().getUserName();
            if (service.userExist(username) == 0) {
                log.trace("User - " + username + " added in database");
                service.insert(new User(username, chatId));
            }
        }
    }

    public void sendMessage(long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setText(text);
        message.setChatId(chatId);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public UserService getService() {
        return service;
    }

    @Override
    public String getBotUsername(){
        return "noisynotice_bot";
    }

    @Override
    public String getBotToken(){
        return "6641248686:AAF9Rf7REkP1Jd9px1AiNUpuNJN176GUWfY";
    }


//    public void sendMessageToAllUsers(String text) {
//        for (long chatId : userProcessedMap.values()) {
//            sendMessage(chatId, text);
//        }
//    }



}