package org.example.strategy;

import org.example.User;

public class DetailedDisplayStrategy implements DisplayStrategy {
    @Override
    public String format(User user) {
        return "[ID: " + user.getId() + "] " + user.getName() + " (" + user.getEmail() + ") | Status: " + user.getUserType() + ".";
    }
}

