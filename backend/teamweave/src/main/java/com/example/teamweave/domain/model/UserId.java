package com.example.teamweave.domain.model;

public record UserId(Long value) {
    public UserId {
        if (value == null)
            throw new IllegalArgumentException("UserId cannot be null");
    }
}
