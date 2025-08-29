package org.example;

import com.github.javafaker.Faker;
import org.example.yaml.YamlUserLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserFactory {
    private static final Faker faker = new Faker();
    private static String yamlFilePath;

    public static void setYamlFilePath(String path) {
        yamlFilePath = path;
    }

    public static List<User> createUsers() {
        List<User> result = new ArrayList<>();
        YamlUserLoader loader = new YamlUserLoader();

        List<User> yamlUsers = new ArrayList<>();
        if (yamlFilePath != null) {
            yamlUsers = loader.loadUsers(yamlFilePath);
        }

        for (int i = 0; i < 3; i++) {
            result.add(yamlUsers.get(i));
        }

        result.add(createUserWithFaker());
        result.add(createUserWithFaker());

        return result;
    }

    private static User createUserWithFaker() {
        UUID id = UUID.randomUUID();
        String name = faker.name().fullName();
        String email = faker.internet().emailAddress();
        UserType userType = faker.bool().bool() ? UserType.PREMIUM : UserType.REGULAR;

        return new User(id, name, email, userType);
    }
}
