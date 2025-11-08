package com.example.teamweave.domain.model;

import java.util.Objects;

public class User {
    private final UserId id;
    private final String email;
    private String passwordHash;

    public User(UserId id, String email, String passwordHash) {
        this.id = Objects.requireNonNull(id);
        this.email = Objects.requireNonNull(email);
        this.passwordHash = Objects.requireNonNull(passwordHash);
    }

    public UserId getId() { return id; }
    public String getEmail() { return email; }
    public String getPasswordHash() { return passwordHash; }

    public void changePassword(String newHash) {
        this.passwordHash = Objects.requireNonNull(newHash);
    }
}
