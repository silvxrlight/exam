package org.example;

import org.example.yaml.YamlUserGenerator;

import javax.swing.*;

public class App {
    public static void main(String[] args) {
        YamlUserGenerator generator = new YamlUserGenerator();
        UserFactory.setYamlFilePath(generator.getYamlFilePath());

        SwingUtilities.invokeLater(() -> new UserUI().setVisible(true));
    }
}
