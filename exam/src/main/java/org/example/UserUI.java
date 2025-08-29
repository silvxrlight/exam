package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import org.example.strategy.*;

public class UserUI extends JFrame {
    private JTextArea textArea;
    private List<User> users = new ArrayList<>();
    private PremiumPublisher publisher = new PremiumPublisher();
    private final LogService logService;
    private JComboBox<String> strategyComboBox;
    private DisplayStrategy currentStrategy;

    public UserUI() {
        setTitle("Пользователи");
        setSize(700, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        logService = new LogService();

        JPanel buttonPanel = new JPanel();

        JButton createUsersButton = new JButton("Создать пользователей");
        JButton showPremiumButton = new JButton("Показать PREMIUM");

        strategyComboBox = new JComboBox<>(new String[]{"Simple", "Detailed", "JSON"});
        strategyComboBox.setSelectedIndex(0);
        currentStrategy = new SimpleDisplayStrategy();

        strategyComboBox.addActionListener(e -> {
            switch ((String) strategyComboBox.getSelectedItem()) {
                case "Detailed" -> currentStrategy = new DetailedDisplayStrategy();
                case "JSON" -> currentStrategy = new JsonDisplayStrategy();
                default -> currentStrategy = new SimpleDisplayStrategy();
            }
        });

        buttonPanel.add(createUsersButton);
        buttonPanel.add(showPremiumButton);
        buttonPanel.add(new JLabel("Формат:"));
        buttonPanel.add(strategyComboBox);

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

        List<User> newUsers = UserFactory.createUsers();
        for (User user : newUsers) {
            users.add(user);

            textArea.append("Пользователь: " + user.getName() + " | " +
                    user.getEmail() + " | Тип: " + user.getUserType() + "\n");

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

            String formatted = currentStrategy.format(user);
            textArea.append(formatted + "\n");
            logService.logUser(user);
            publisher.unsubscribe(subscription[0]);
        };

        return subscription[0];
    }

    private void showPremiumUsers() {
        textArea.setText("");
        publisher.notifySubscribers(new PremiumEvent());
    }
}
