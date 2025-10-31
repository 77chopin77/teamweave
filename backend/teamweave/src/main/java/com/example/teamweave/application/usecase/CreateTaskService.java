package com.example.teamweave.application.usecase;

import com.example.teamweave.infrastructure.persistence.TaskJpaRepository;
import com.example.teamweave.infrastructure.persistence.entity.TaskJpaEntity;
import com.example.teamweave.infrastructure.security.JwtProvider;
import org.springframework.stereotype.Service;

@Service
public class CreateTaskService {

    private final TaskJpaRepository tasks;
    private final JwtProvider jwt;

    public CreateTaskService(TaskJpaRepository tasks, JwtProvider jwt) {
        this.tasks = tasks;
        this.jwt = jwt;
    }

    public void createTask(String token, String title) {
        Long userId = Long.parseLong(jwt.validateAndGetUserId(token));  // ✅ JWT返却値をLongに
        var task = new TaskJpaEntity();
        task.setUserId(userId);
        task.setTitle(title);
        tasks.save(task);  // ✅ idは自動生成なのでsetId不要
    }
}
