package org.example.db;

import org.example.User;

import java.sql.*;
import java.time.LocalDateTime;

public class DatabaseService {
    private static final String DB_URL = "jdbc:sqlite:users.db";
    private Connection connection;
    private int currentSessionId;

    public void init() {
        try {
            connection = DriverManager.getConnection(DB_URL);
            createTables();
            startSession();
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при инициализации БД", e);
        }
    }

    private void createTables() throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("""
                    CREATE TABLE IF NOT EXISTS sessions (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        start_time TIMESTAMP NOT NULL,
                        description VARCHAR(255)
                    )
                    """);

            stmt.execute("""
                    CREATE TABLE IF NOT EXISTS users (
                        id VARCHAR(36) PRIMARY KEY,
                        session_id INTEGER,
                        name VARCHAR(255),
                        email VARCHAR(255),
                        user_type VARCHAR(20),
                        data_source VARCHAR(20),
                        FOREIGN KEY (session_id) REFERENCES sessions(id) ON DELETE CASCADE
                    )
                    """);
        }
    }

    private void startSession() throws SQLException {
        String sql = "INSERT INTO sessions (start_time, description) VALUES (?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, LocalDateTime.now().toString());
            ps.setString(2, "Сеанс от " + LocalDateTime.now());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    currentSessionId = rs.getInt(1);
                }
            }
        }
    }

    public void saveUser(User user, String dataSource) {
        String sql = "INSERT INTO users (id, session_id, name, email, user_type, data_source) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, user.getId().toString());
            ps.setInt(2, currentSessionId);
            ps.setString(3, user.getName());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getUserType().toString());
            ps.setString(6, dataSource);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void resetDatabase() {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("DELETE FROM users");
            stmt.execute("DELETE FROM sessions");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            if (connection != null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
