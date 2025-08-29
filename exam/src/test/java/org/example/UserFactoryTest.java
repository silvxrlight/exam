package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class UserFactoryTest {

    @Test
    void testCreateUserNotNull() {
        User user = UserFactory.createUserWithFaker();
        assertNotNull(user, "Фабрика должна возвращать объект User");
    }

    @Test
    void testUserFieldsNotNull() {
        User user = UserFactory.createUserWithFaker();

        assertNotNull(user.getId(), "ID пользователя не должен быть null");
        assertNotNull(user.getName(), "Имя пользователя не должно быть null");
        assertNotNull(user.getEmail(), "Email пользователя не должен быть null");
        assertNotNull(user.getUserType(), "Тип пользователя не должен быть null");
    }

    @Test
    void testUserTypeIsValid() {
        User user = UserFactory.createUserWithFaker();
        assertTrue(
                user.getUserType() == UserType.REGULAR || user.getUserType() == UserType.PREMIUM,
                "UserType должен быть REGULAR или PREMIUM"
        );
    }

    @Test
    void testCreateUsersReturnsFiveUsers() {
        List<User> users = UserFactory.createUsers();
        assertEquals(5, users.size(), "Метод createUsers() должен возвращать 5 пользователей");
    }
}
