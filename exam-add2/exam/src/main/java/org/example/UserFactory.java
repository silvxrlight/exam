package org.example;

import com.github.javafaker.Faker;
import java.util.UUID;

/**
 * Фабрика для создания объектов {@link User}.
 * Использует библиотеку Faker для генерации случайных данных.
 */
public class UserFactory {

    /** Экземпляр Faker для генерации случайных данных */
    private static final Faker faker = new Faker();

    /**
     * Создаёт нового пользователя с уникальным идентификатором,
     * случайным именем, email и типом пользователя.
     *
     * @return новый объект {@link User}
     */
    public static User createUser() {
        UUID id = UUID.randomUUID();
        String name = faker.name().fullName();
        String email = faker.internet().emailAddress();
        UserType userType = faker.bool().bool() ? UserType.PREMIUM : UserType.REGULAR;

        return new User(id, name, email, userType);
    }
}
