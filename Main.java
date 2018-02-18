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
            botsApi.registerBot(new ProphetBot());
            System.out.println("Prophet bot start succesfully!");
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}