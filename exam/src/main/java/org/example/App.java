package org.example;

import org.example.db.DatabaseService;

import javax.swing.*;

public class App {
    public static void main(String[] args) {

        DatabaseService dbService = new DatabaseService();
        dbService.init();

        UserService userService = new UserService();

        SwingUtilities.invokeLater(() -> new UserUI(dbService, userService).setVisible(true));

        Runtime.getRuntime().addShutdownHook(new Thread(dbService::close));
    }
}
