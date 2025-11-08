package com.example.teamweave.application.usecase;

import com.example.teamweave.application.port.out.TaskRepositoryPort;
import com.example.teamweave.domain.model.Task;
import com.example.teamweave.domain.model.TaskId;
import com.example.teamweave.domain.model.UserId;
import com.example.teamweave.infrastructure.security.JwtProvider;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CreateTaskService {

    private final TaskRepositoryPort taskRepository;
    private final JwtProvider jwt;

    public CreateTaskService(TaskRepositoryPort taskRepository, JwtProvider jwt) {
        this.taskRepository = taskRepository;
        this.jwt = jwt;
    }

    public void createTask(String token, String title) {
        UUID userUuid = UUID.fromString(jwt.validateAndGetUserId(token));

        // ✅ Domain型で構築
        var task = new Task(new TaskId(UUID.randomUUID()), title);
        task.setUser(new UserId(userUuid));
        task.setStatus(Task.Status.TODO);

        taskRepository.save(task);
    }
}
