package com.example.teamweave.domain.model;

public record TaskId(Long value) {
    public TaskId {
        if (value == null)
            throw new IllegalArgumentException("TaskId cannot be null");
    }
}
