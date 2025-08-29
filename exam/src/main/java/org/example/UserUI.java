package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class UserUI extends JFrame {
    private JTextArea textArea;
    private List<User> users = new ArrayList<>();
    private PremiumPublisher publisher = new PremiumPublisher();
    private final LogService logService; // новый сервис для логов

    public UserUI() {
        setTitle("Пользователи");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        logService = new LogService(); // создаём Excel при запуске

        JPanel buttonPanel = new JPanel();
        JButton createUsersButton = new JButton("Создать пользователей");
        JButton showPremiumButton = new JButton("Показать PREMIUM");

        buttonPanel.add(createUsersButton);
        buttonPanel.add(showPremiumButton);

        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        add(buttonPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        createUsersButton.addActionListener(e -> createUsers());
        showPremiumButton.addActionListener(e -> showPremiumUsers());
    }

    private void createUsers() {
        users.clear();
        textArea.setText("");
        publisher = new PremiumPublisher();

        for (int i = 0; i < 5; i++) {
            User user = UserFactory.createUser();
            users.add(user);

            textArea.append("Пользователь: " + user.getName() + " | " + user.getEmail() +
                    " | Тип: " + user.getUserType() + "\n");

            if (user.getUserType() == UserType.PREMIUM) {
                Consumer<PremiumEvent> subscription = createSubscription(user);
                publisher.subscribe(subscription);
            }
        }

        JOptionPane.showMessageDialog(this, "Пользователи созданы");
    }

    private Consumer<PremiumEvent> createSubscription(User user) {
        final Consumer<PremiumEvent>[] subscription = new Consumer[1];

        subscription[0] = event -> {
            textArea.append("PREMIUM: " + user.getName() + " | " + user.getEmail() + "\n");
            logService.logUser(user); // логируем PREMIUM в Excel
            publisher.unsubscribe(subscription[0]);
        };

        return subscription[0];
    }

    private void showPremiumUsers() {
        textArea.setText("");
        publisher.notifySubscribers(new PremiumEvent());
    }
}
