package com.example.teamweave.application.usecase;

import com.example.teamweave.application.port.out.TaskRepositoryPort;
import com.example.teamweave.infrastructure.security.JwtProvider;
import org.springframework.stereotype.Service;
import java.util.UUID;

/* 指定したユーザーのタスクを削除するユースケース */
@Service
public class DeleteTaskService {

    // Taskデータを削除・取得するための抽象リポジトリ（DB操作を隠す）
    private final TaskRepositoryPort taskRepository;

    // JWTトークンの検証とユーザーID抽出を行うコンポーネント
    private final JwtProvider jwt;

     // コンストラクタで依存性を注入（DI）
    public DeleteTaskService(TaskRepositoryPort taskRepository, JwtProvider jwt) {
        this.taskRepository = taskRepository;
        this.jwt = jwt;
    }

    // タスク削除ユースケース
    public void deleteTask(String token, String id) {
        UUID userId = UUID.fromString(jwt.validateAndGetUserId(token));
        UUID taskId = UUID.fromString(id);
        taskRepository.deleteByIdAndUserId(taskId, userId);
    }
}
