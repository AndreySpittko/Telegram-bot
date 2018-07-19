package com.company;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

public class Main {

    public static void main(String[] args) {
	// write your code here

//        NetworkManager.disabless();
        //
        ApiContextInitializer.init();

        TelegramBotsApi api = new TelegramBotsApi();

        try {
            api.registerBot(new CivilEngineeringBot());
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }


    }



}
