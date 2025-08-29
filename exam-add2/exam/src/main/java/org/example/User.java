package org.example;

import java.util.UUID;

/**
 * Класс, представляющий пользователя системы.
 * Содержит уникальный идентификатор, имя, email и тип пользователя.
 */
public class User {

    /** Уникальный идентификатор пользователя */
    private UUID id;

    /** Имя пользователя */
    private String name;

    /** Электронная почта пользователя */
    private String email;

    /** Тип пользователя (PREMIUM или REGULAR) */
    private UserType userType;

    /**
     * Конструктор для создания нового пользователя.
     *
     * @param id уникальный идентификатор пользователя
     * @param name имя пользователя
     * @param email электронная почта пользователя
     * @param userType тип пользователя
     */
    public User(UUID id, String name, String email, UserType userType) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.userType = userType;
    }

    /**
     * Возвращает уникальный идентификатор пользователя.
     *
     * @return id пользователя
     */
    public UUID getId() {
        return id;
    }

    /**
     * Возвращает имя пользователя.
     *
     * @return имя пользователя
     */
    public String getName() {
        return name;
    }

    /**
     * Возвращает электронную почту пользователя.
     *
     * @return email пользователя
     */
    public String getEmail() {
        return email;
    }

    /**
     * Возвращает тип пользователя.
     *
     * @return тип пользователя (UserType)
     */
    public UserType getUserType() {
        return userType;
    }
}
