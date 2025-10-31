package com.example.teamweave.application.usecase;

import com.example.teamweave.infrastructure.persistence.TaskJpaRepository;
import com.example.teamweave.infrastructure.persistence.entity.TaskJpaEntity;
import com.example.teamweave.infrastructure.security.JwtProvider;
import org.springframework.stereotype.Service;

@Service
public class DeleteTaskService {

    private final TaskJpaRepository tasks;
    private final JwtProvider jwt;

    public DeleteTaskService(TaskJpaRepository tasks, JwtProvider jwt) {
        this.tasks = tasks;
        this.jwt = jwt;
    }

    public void deleteTask(String token, String id) {
        Long userId = Long.parseLong(jwt.validateAndGetUserId(token)); // ✅ Long化
        TaskJpaEntity task = tasks.findById(Long.parseLong(id)) // ✅ Long化
                .orElseThrow(() -> new IllegalArgumentException("Task not found"));

        if (!task.getUserId().equals(userId)) {
            throw new SecurityException("Forbidden: You don't own this task");
        }

        tasks.delete(task);
    }
}
