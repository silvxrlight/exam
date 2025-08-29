package org.example.yaml;

import org.example.User;
import org.example.UserType;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class YamlUserLoader {

    public List<User> loadUsers(String yamlFilePath) {
        List<User> users = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(yamlFilePath)) {
            Yaml yaml = new Yaml();
            List<Map<String, Object>> data = yaml.load(fis);

            for (Map<String, Object> entry : data) {
                UUID id = UUID.fromString(entry.get("id").toString());
                String name = entry.get("name").toString();
                String email = entry.get("email").toString();
                UserType type = UserType.valueOf(entry.get("userType").toString());
                users.add(new User(id, name, email, type));
            }
        } catch (IOException e) {
            System.err.println("Ошибка при загрузке YAML: " + e.getMessage());
        }

        return users;
    }
}
