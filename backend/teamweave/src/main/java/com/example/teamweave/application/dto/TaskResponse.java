package com.example.teamweave.application.dto;

import com.example.teamweave.domain.model.Task;

public record TaskResponse(Long id, String title, String status) {
    public static TaskResponse from(Task task) {
        return new TaskResponse(
            task.getId().value(),
            task.getTitle(),
            task.getStatus().name()
        );
    }
}
