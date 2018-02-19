/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ibotlold.nefuprophet;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

/**
 *
 * @author ibotlold
 */
public class ProphetBot extends TelegramLongPollingBot {
    public Connection db;
    public void connectToDB() throws TelegramApiException{
        Connection connection = null;
        try {
            System.out.println("Connecting to DB...");
            connection = getConnection();
            System.out.println("Connected succesfully!");
        } catch (URISyntaxException | SQLException e) {
            throw new TelegramApiException("\nDB connection error!\nMessage: " + e.getMessage());
        } finally {
            if (connection != null) {
                db = connection;
            }
        }
    }
    private static Connection getConnection() throws URISyntaxException, SQLException {
        URI dbUri = new URI(System.getenv("DATABASE_URL"));
        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
        return DriverManager.getConnection(dbUrl, username, password);
    }
    
    @SuppressWarnings("CallToPrintStackTrace")
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
