package com.java;


import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.*;
import java.util.HashMap;


public final class Bot extends TelegramLongPollingBot {
    private HashMap<String, Long> userProcessedMap = new HashMap<>();
    File file = new File("C:\\Users\\naminari\\IdeaProjects\\sender\\src\\main\\java\\telegram\\chatIds.txt");


    @Override
    public String getBotUsername(){
        return "noisynotice_bot";
    }

    @Override
    public String getBotToken(){
        return "6641248686:AAF9Rf7REkP1Jd9px1AiNUpuNJN176GUWfY";
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            Long chatId = update.getMessage().getChatId();
            String username = update.getMessage().getFrom().getUserName();
            if (!userProcessedMap.containsKey(username)) {
                userProcessedMap.put(username,chatId);
                saveHashMapToFile(userProcessedMap);
            }
        }
    }

    public void saveHashMapToFile(HashMap<String, Long> map) {
        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(map);
            oos.close();
            fos.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
public HashMap<String, Long> getMap() {
    HashMap<String, Long> map = null;
    try {
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        map = (HashMap) ois.readObject();
        ois.close();
        fis.close();
    } catch (IOException ioe) {
        ioe.printStackTrace();
    } catch (ClassNotFoundException c) {
        System.out.println("Class not found");
        c.printStackTrace();
    }
    return map;
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

    public void sendMessageToAllUsers(String text) {
        for (long chatId : userProcessedMap.values()) {
            sendMessage(chatId, text);
        }
    }



}