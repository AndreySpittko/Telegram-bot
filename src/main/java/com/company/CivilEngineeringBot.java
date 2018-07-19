package com.company;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CivilEngineeringBot extends TelegramLongPollingBot {       // point 2

    private final String API = "564630703:AAFCW0Jsq5cVn9N9u0qv9CsgNc_jXFRe6_E";
    private final String CIVIL_BOT = "My bot";
    private String db = "mongodb://java2:monkey2@ds241121.mlab.com:41121/heroku_sbfcgdq8";

    @Override
    public void onUpdateReceived(Update update) {
        SendMessage sendMessage = new SendMessage();

        if (update.hasMessage()) {
            String text = update.getMessage().getText();
            sendMessage.setChatId(update.getMessage().getChatId());
            if (text.equals("/start")) {
                InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
                InlineKeyboardButton inlineKeyboardButtonYes = new InlineKeyboardButton();
                InlineKeyboardButton inlineKeyboardButtonNo = new InlineKeyboardButton();


                List<List<InlineKeyboardButton>> keyboardButton = new ArrayList<>();
                List<InlineKeyboardButton> row = new ArrayList<>();

                inlineKeyboardButtonYes.setText("Yes");
                inlineKeyboardButtonYes.setCallbackData("yes");

                inlineKeyboardButtonNo.setText("No");
                inlineKeyboardButtonNo.setCallbackData("no");

                row.add(inlineKeyboardButtonYes);
                row.add(inlineKeyboardButtonNo);

                keyboardButton.add(row);

                inlineKeyboardMarkup.setKeyboard(keyboardButton);

                sendMessage.setText("Do you want this?");
                sendMessage.setReplyMarkup(inlineKeyboardMarkup);
            }
        } else if (update.hasCallbackQuery()) {
            String callBackData = update.getCallbackQuery().getData();
            sendMessage.setChatId(update.getCallbackQuery().getMessage().getChatId());
            if (callBackData.equals("yes")) {
                ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();

                List<KeyboardRow> keyboard = new ArrayList<>();
                KeyboardRow row = new KeyboardRow();
                row.add("Every hour \uD83D\uDC69\u200D\uD83C\uDFA8");
                row.add("Every day");
                keyboard.add(row);

                replyKeyboardMarkup.setKeyboard(keyboard);
                sendMessage.setText("Select replasy time");
                sendMessage.setReplyMarkup(replyKeyboardMarkup);

            } else if (callBackData.equals("no")) {
                sendMessage.setText("Ok, send '/start' again later.");
            }
        }

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }


//
//        String text = update.getMessage().getText();
//        long chatId = update.getMessage().getChatId();
//
//        MongoClientURI mongoClientURI = new MongoClientURI(db);
//        MongoClient mongoClient = new MongoClient(mongoClientURI);
//        MongoDatabase dataBase = mongoClient.getDatabase(mongoClientURI.getDatabase());
//        MongoCollection<Document> expends = dataBase.getCollection("Andrey1");
//
//        SendMessage sendMessage = new SendMessage().setChatId(chatId);
//
////        SendMessage sendMessage = new SendMessage();
//        sendMessage.setChatId(text);
//        sendMessage.setChatId(chatId);
//
//        if(text.equals("/start")) {
//            sendMessage.setText("Enter expenses");
//
//        } else if(text.equals("/all")) {
//            FindIterable<Document> all = expends.find();
//            double suma = 0;
//            for(Document doc : all) {
//                for(Map.Entry<String, Object> entry : doc.entrySet()) {
//                    if(!entry.getKey().equals("_id")) {
//                        String value = (String) entry.getValue();
//                        suma += Double.valueOf(value);
//                    }
//                    System.out.println(entry.getKey());
//                }
//            }
//        } else {
//            Document document = new Document();
////            expends.find(new Document());
//            document.append(Integer.toString(update.getMessage().getMessageId()), text);
//            expends.insertOne(document);
//
//            sendMessage.setText("Added");
//        }
//
//        try {
//            execute(sendMessage);
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
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
