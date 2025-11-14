package com.example.teamweave.application.usecase;

import com.example.teamweave.application.port.out.TaskRepositoryPort;
import com.example.teamweave.domain.model.Task;
import com.example.teamweave.domain.model.TaskId;
import com.example.teamweave.domain.model.UserId;
import com.example.teamweave.infrastructure.security.JwtProvider;
import org.springframework.stereotype.Service;

import java.util.UUID;

/* 新しいタスクを作成して、ログイン中のユーザーに紐づけて保存する */
@Service
public class CreateTaskService {

    // タスクを保存・取得するためのポート（Repositoryの抽象化）
    private final TaskRepositoryPort taskRepository;

    //  JWTの検証・ユーザーID抽出を担当
    private final JwtProvider jwt;

    // コンストラクタでDI（依存性注入）
    public CreateTaskService(TaskRepositoryPort taskRepository, JwtProvider jwt) {
        this.taskRepository = taskRepository;
        this.jwt = jwt;
    }

    // タスクを作成するユースケース（目的と手順の宣言）
    public void createTask(String token, String title) {
        UUID userUuid = UUID.fromString(jwt.validateAndGetUserId(token));

        // ✅ Domain型で構築
        var task = new Task(new TaskId(UUID.randomUUID()), title);
        task.setUser(new UserId(userUuid));
        task.setStatus(Task.Status.TODO);

        taskRepository.save(task);
    }
}
