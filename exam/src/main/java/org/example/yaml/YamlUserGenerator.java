package org.example.yaml;

import com.github.javafaker.Faker;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class YamlUserGenerator {
    private static final Faker faker = new Faker();
    private final String yamlFilePath;

    public YamlUserGenerator() {
        try {
            File configDir = new File("config");
            if (!configDir.exists()) {
                configDir.mkdirs();
            }

            String timestamp = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
            yamlFilePath = "config/users_data_" + timestamp + ".yaml";

            generateYamlFile();
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при генерации YAML файла", e);
        }
    }

    private void generateYamlFile() throws IOException {
        List<Map<String, Object>> users = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Map<String, Object> userMap = new LinkedHashMap<>();
            userMap.put("id", UUID.randomUUID().toString());
            userMap.put("name", faker.name().fullName());
            userMap.put("email", faker.internet().emailAddress());
            userMap.put("userType", faker.bool().bool() ? "PREMIUM" : "REGULAR");
            users.add(userMap);
        }

        DumperOptions options = new DumperOptions();
        options.setPrettyFlow(true);
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        Yaml yaml = new Yaml(options);

        try (FileWriter writer = new FileWriter(yamlFilePath)) {
            yaml.dump(users, writer);
        }
    }

    public String getYamlFilePath() {
        return yamlFilePath;
    }
}
