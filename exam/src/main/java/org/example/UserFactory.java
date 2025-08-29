package org.example;

import com.github.javafaker.Faker;
import java.util.UUID;

public class UserFactory {
    private static final Faker faker = new Faker();

    public static User createUser() {
        UUID id = UUID.randomUUID();
        String name = faker.name().fullName();
        String email = faker.internet().emailAddress();
        UserType userType = faker.bool().bool() ? UserType.PREMIUM : UserType.REGULAR;

        return new User(id, name, email,userType);
    }
}
