/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ibotlold.nefuprophet;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;


/**
 *
 * @author ibotlold
 */
public class Main {
    public static void main(String[] args) {
        ApiContextInitializer.init();
        
        TelegramBotsApi botsApi = new TelegramBotsApi();

        try {
            System.out.println("Launching bot...");
            ProphetBot bot = new ProphetBot();
            botsApi.registerBot(bot);
            System.out.println("Bot started succesfully!");
            bot.connectToDB();
        } catch (TelegramApiException e) {
            System.out.println("!Bot crashed!" + e.getMessage());
            e.printStackTrace();
        }
    }
}