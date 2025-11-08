package com.example.teamweave.application.port.in;

import com.example.teamweave.application.usecase.dto.TaskDto;

public interface CreateTasksUseCase {
    TaskDto handle(CreateTaskCommand cmd);

    record CreateTaskCommand(String title, String description, String assigneeId, String dueDateIso) {
    }
}
