package org.example;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class UserFactoryTest {

    @Test
    void testCreateUserNotNullFields() {
        User user = UserFactory.createUser();

        assertNotNull(user, "User should not be null");
        assertNotNull(user.getId(), "User ID should not be null");
        assertNotNull(user.getName(), "User name should not be null");
        assertFalse(user.getName().isEmpty(), "User name should not be empty");
        assertNotNull(user.getEmail(), "User email should not be null");
        assertFalse(user.getEmail().isEmpty(), "User email should not be empty");
        assertNotNull(user.getUserType(), "User type should not be null");
    }

    @Test
    void testUserTypeValid() {
        User user = UserFactory.createUser();

        assertTrue(user.getUserType() == UserType.PREMIUM || user.getUserType() == UserType.REGULAR,
                "User type should be PREMIUM or REGULAR");
    }

    @Test
    void testUserEmailContainsAtSymbol() {
        User user = UserFactory.createUser();

        assertNotNull(user.getEmail(), "Email should not be null");
        assertTrue(user.getEmail().contains("@"),
                "Email should contain '@' character");
    }
}
