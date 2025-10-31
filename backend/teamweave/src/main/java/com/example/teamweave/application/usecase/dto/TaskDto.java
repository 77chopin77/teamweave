package com.example.teamweave.application.usecase.dto;

import com.example.teamweave.domain.model.Task;

public record TaskDto(Long id, String title, String description, Long assigneeId,
                      String status, String dueDateIso) {

    public static TaskDto from(Task t) {
        return new TaskDto(
            t.getId().value(),
            t.getTitle(),
            t.getDescription(),
            t.getAssignee() == null ? null : t.getAssignee().value(),
            t.getStatus().name(),
            t.getDueDate() == null ? null : t.getDueDate().toString()
        );
    }
}
