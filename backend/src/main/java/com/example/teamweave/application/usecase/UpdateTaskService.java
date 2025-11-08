package com.example.teamweave.application.usecase;

import com.example.teamweave.application.port.out.TaskRepositoryPort;
import com.example.teamweave.domain.model.Task;
import com.example.teamweave.infrastructure.security.JwtProvider;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class UpdateTaskService {

    private final TaskRepositoryPort taskRepository;
    private final JwtProvider jwt;

    public UpdateTaskService(TaskRepositoryPort taskRepository, JwtProvider jwt) {
        this.taskRepository = taskRepository;
        this.jwt = jwt;
    }

    public void updateTask(String token, String id, String newTitle) {
        UUID userId = UUID.fromString(jwt.validateAndGetUserId(token));
        UUID taskId = UUID.fromString(id);

        Task task = taskRepository.findByIdAndUserId(taskId, userId)
                .orElseThrow(() -> new IllegalArgumentException("Task not found"));

        task.setTitle(newTitle);
        taskRepository.save(task);
    }
}
