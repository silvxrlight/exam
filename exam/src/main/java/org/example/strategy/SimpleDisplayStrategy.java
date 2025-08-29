package org.example.strategy;

import org.example.User;

public class SimpleDisplayStrategy implements DisplayStrategy {
    @Override
    public String format(User user) {
        return user.getName() + " | " + user.getEmail();
    }
}
