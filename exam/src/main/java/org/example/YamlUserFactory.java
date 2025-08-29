package org.example;

import org.example.yaml.YamlUserLoader;
import java.util.List;

public class YamlUserFactory extends UserFactory {
    private final String yamlFilePath;
    private final int userIndex;

    public YamlUserFactory(String yamlFilePath, int userIndex) {
        this.yamlFilePath = yamlFilePath;
        this.userIndex = userIndex;
    }

    @Override
    public User createUser() {
        YamlUserLoader loader = new YamlUserLoader();
        List<User> users = loader.loadUsers(yamlFilePath);

        if (userIndex < users.size()) {
            return users.get(userIndex);
        } else {
            throw new IllegalArgumentException("В YAML нет пользователя с индексом " + userIndex);
        }
    }
}
