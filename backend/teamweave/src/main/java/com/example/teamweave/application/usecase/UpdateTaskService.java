package com.example.teamweave.application.usecase;

import com.example.teamweave.infrastructure.persistence.TaskJpaRepository;
import com.example.teamweave.infrastructure.persistence.entity.TaskJpaEntity;
import com.example.teamweave.infrastructure.security.JwtProvider;
import org.springframework.stereotype.Service;

@Service
public class UpdateTaskService {

    private final TaskJpaRepository tasks;
    private final JwtProvider jwt;

    public UpdateTaskService(TaskJpaRepository tasks, JwtProvider jwt) {
        this.tasks = tasks;
        this.jwt = jwt;
    }

    public void updateTask(String token, String id, String newTitle) {
        Long userId = Long.parseLong(jwt.validateAndGetUserId(token));

        TaskJpaEntity task = tasks.findById(Long.parseLong(id))
                .orElseThrow(() -> new IllegalArgumentException("Task not found"));

        if (!task.getUserId().equals(userId)) {
            throw new SecurityException("Permission denied");
        }

        task.setTitle(newTitle);
        tasks.save(task);
    }
}
