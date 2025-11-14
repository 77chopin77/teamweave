package com.example.teamweave.presentation.dto;

import com.example.teamweave.domain.model.Task;
import java.util.UUID;

public record TaskResponse(UUID id, String title, String status) {
    public static TaskResponse from(Task task) {
        return new TaskResponse(
            task.getId().value(),
            task.getTitle(),
            task.getStatus().name()
        );
    }
}
