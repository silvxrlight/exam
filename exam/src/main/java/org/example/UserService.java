package org.example;

import org.example.yaml.YamlUserGenerator;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    public List<User> generateUsers() {
        List<UserFactory> factories = new ArrayList<>();

        // Генерируем YAML-файл (10 пользователей)
        String yamlPath = new YamlUserGenerator().getYamlFilePath();

        // 3 из YAML
        factories.add(new YamlUserFactory(yamlPath, 0));
        factories.add(new YamlUserFactory(yamlPath, 1));
        factories.add(new YamlUserFactory(yamlPath, 2));

        // 2 из Faker
        factories.add(new FakerUserFactory());
        factories.add(new FakerUserFactory());

        // Создаём пользователей через фабрики
        List<User> users = new ArrayList<>();
        for (UserFactory factory : factories) {
            users.add(factory.createUser());
        }

        return users;
    }
}
