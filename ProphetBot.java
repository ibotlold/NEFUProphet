/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ibotlold.nefuprophet;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

/**
 *
 * @author ibotlold
 */
public class ProphetBot extends TelegramLongPollingBot {
    public void onUpdateReceived(Update update) {
        // TODO
        if (update.hasMessage() && update.getMessage().hasText()) {
        SendMessage message = new SendMessage() // Create a SendMessage object with mandatory fields
                .setChatId(update.getMessage().getChatId())
                .setText(update.getMessage().getChat().getFirstName() + ", " + update.getMessage().getText().toLowerCase());
        try {
            execute(message); // Call method to send the message
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        }
    }

    @Override
    public String getBotUsername() {
        // TODO
        return "NEFUProphet";
    }

    @Override
    public String getBotToken() {
        // TODO
        return System.getenv("BOT_TOKEN");
    }
}
