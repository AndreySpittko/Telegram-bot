package com.company;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class CivilEngineeringBot extends TelegramLongPollingBot {       // point 2

    private final String API = "564630703:AAFCW0Jsq5cVn9N9u0qv9CsgNc_jXFRe6_E";
    private final String CIVIL_BOT = "My bot";

    @Override
    public void onUpdateReceived(Update update) {
        if (!update.hasMessage()) {
            return;
        }

        String text = update.getMessage().getText();
        long chatId = update.getMessage().getChatId();

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(text);
        sendMessage.setChatId(chatId);

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }


    }

    @Override
    public String getBotUsername() {
        return CIVIL_BOT;
    }

    @Override
    public String getBotToken() {
        return API;
    }
}
