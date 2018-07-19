package com.company;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.Map;

public class CivilEngineeringBot extends TelegramLongPollingBot {       // point 2

    private final String API = "564630703:AAFCW0Jsq5cVn9N9u0qv9CsgNc_jXFRe6_E";
    private final String CIVIL_BOT = "My bot";
    private String db = "mongodb://java2:monkey2@ds241121.mlab.com:41121/heroku_sbfcgdq8";

    @Override
    public void onUpdateReceived(Update update) {
        if (!update.hasMessage()) {
            return;
        }

        String text = update.getMessage().getText();
        long chatId = update.getMessage().getChatId();

        MongoClientURI mongoClientURI = new MongoClientURI(db);
        MongoClient mongoClient = new MongoClient(mongoClientURI);
        MongoDatabase dataBase = mongoClient.getDatabase(mongoClientURI.getDatabase());
        MongoCollection<Document> expends = dataBase.getCollection("Andrey1");

        SendMessage sendMessage = new SendMessage().setChatId(chatId);

//        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(text);
        sendMessage.setChatId(chatId);

        if(text.equals("/start")) {
            sendMessage.setText("Enter expenses");

        } else if(text.equals("/all")) {
            FindIterable<Document> all = expends.find();
            double suma = 0;
            for(Document doc : all) {
                for(Map.Entry<String, Object> entry : doc.entrySet()) {
                    if(!entry.getKey().equals("_id")) {
                        String value = (String) entry.getValue();
                        suma += Double.valueOf(value);
                    }
                    System.out.println(entry.getKey());
                }
            }
        } else {
            Document document = new Document();
//            expends.find(new Document());
            document.append(Integer.toString(update.getMessage().getMessageId()), text);
            expends.insertOne(document);

            sendMessage.setText("Added");
        }

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

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
