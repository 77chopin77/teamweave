package com.example.teamweave.domain.model;

import java.util.UUID;

public class TaskId {
    private final UUID value;

    public TaskId(UUID value) {
        this.value = value;
    }

    public UUID value() {
        return value;
    }
}
