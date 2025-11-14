package com.example.teamweave.application.usecase;

import com.example.teamweave.application.port.out.TaskRepositoryPort;
import com.example.teamweave.domain.model.UserId;
import com.example.teamweave.domain.model.Task;
import com.example.teamweave.infrastructure.security.JwtProvider;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/* タスクを取得するクラス */
@Service // SpringによりDIコンテナへ登録（ユースケースサービスとして管理）
public class GetTasksService {

    // タスク取得・保存などのデータアクセスを抽象化したリポジトリ
    private final TaskRepositoryPort taskRepository;

    // JWTトークンを解析し、ユーザー情報を取り出すためのコンポーネント
    private final JwtProvider jwtProvider;

    // コンストラクタで依存を注入（DI）
    public GetTasksService(TaskRepositoryPort taskRepository, JwtProvider jwtProvider) {
        this.taskRepository = taskRepository;
        this.jwtProvider = jwtProvider;
    }

    public List<Task> getTasks(String token) {
        // JWTの中から userId(UUID文字列) を取り出し
        String userIdStr = jwtProvider.validateAndGetUserId(token);

        // UUIDに変換（ここで NumberFormatException は起きなくなる）
        UUID userId = UUID.fromString(userIdStr);

        // RepositoryPort経由で取得
        return taskRepository.findByUserId(new UserId(userId));
    }
}
