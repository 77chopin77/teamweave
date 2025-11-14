package com.example.teamweave.application.usecase;

import com.example.teamweave.application.port.out.TaskRepositoryPort;
import com.example.teamweave.domain.model.Task;
import com.example.teamweave.infrastructure.security.JwtProvider;
import org.springframework.stereotype.Service;
import java.util.UUID;

/* 既存のタスクのタイトルを更新する */
@Service
public class UpdateTaskService {

    // タスクの永続化を担当するリポジトリポート（DB操作の抽象化）
    private final TaskRepositoryPort taskRepository;

    // JWTトークンの検証とユーザーID取得を行うコンポーネント
    private final JwtProvider jwt;

    // コンストラクタで依存を注入（DI）
    public UpdateTaskService(TaskRepositoryPort taskRepository, JwtProvider jwt) {
        this.taskRepository = taskRepository;
        this.jwt = jwt;
    }

    //  タスクのタイトルを更新するユースケース
    public void updateTask(String token, String id, String newTitle) {

        //  JWTからユーザーID（UUID文字列）を取り出してUUID型に変換
        UUID userId = UUID.fromString(jwt.validateAndGetUserId(token));

        // タスクIDをUUID型に変換
        UUID taskId = UUID.fromString(id);

        // ユーザーIDとタスクIDの両方に一致するタスクを取得
        Task task = taskRepository.findByIdAndUserId(taskId, userId)
                .orElseThrow(() -> new IllegalArgumentException("Task not found"));

        // タスクのタイトルを新しい値に更新
        task.setTitle(newTitle);

        // 更新されたタスクを保存（DBに反映）
        taskRepository.save(task);
    }
}
