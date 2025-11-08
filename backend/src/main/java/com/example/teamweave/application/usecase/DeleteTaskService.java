package com.example.teamweave.application.usecase;

import com.example.teamweave.application.port.out.TaskRepositoryPort;
import com.example.teamweave.infrastructure.security.JwtProvider;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class DeleteTaskService {

    private final TaskRepositoryPort taskRepository;
    private final JwtProvider jwt;

    public DeleteTaskService(TaskRepositoryPort taskRepository, JwtProvider jwt) {
        this.taskRepository = taskRepository;
        this.jwt = jwt;
    }

    public void deleteTask(String token, String id) {
        UUID userId = UUID.fromString(jwt.validateAndGetUserId(token));
        UUID taskId = UUID.fromString(id);
        taskRepository.deleteByIdAndUserId(taskId, userId);
    }
}
