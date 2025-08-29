package org.example.strategy;

import org.example.User;

public class JsonDisplayStrategy implements DisplayStrategy {
    @Override
    public String format(User user) {
        return "{\"id\": \"" + user.getId() + "\", " +
                "\"name\": \"" + user.getName() + "\", " +
                "\"email\": \"" + user.getEmail() + "\", " +
                "\"userType\": \"" + user.getUserType() + "\" }";
    }
}
