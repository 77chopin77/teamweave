package com.example.teamweave.application.usecase;

import com.example.teamweave.application.port.out.TaskRepositoryPort;
import com.example.teamweave.domain.model.UserId;
import com.example.teamweave.domain.model.Task;
import com.example.teamweave.infrastructure.security.JwtProvider;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GetTasksService {

    private final TaskRepositoryPort taskRepository;
    private final JwtProvider jwtProvider;

    public GetTasksService(TaskRepositoryPort taskRepository, JwtProvider jwtProvider) {
        this.taskRepository = taskRepository;
        this.jwtProvider = jwtProvider;
    }

    public List<Task> getTasks(String token) {
        // ✅ JWTの中から userId(UUID文字列) を取り出し
        String userIdStr = jwtProvider.validateAndGetUserId(token);

        // ✅ UUIDに変換（ここで NumberFormatException は起きなくなる）
        UUID userId = UUID.fromString(userIdStr);

        // ✅ RepositoryPort経由で取得
        return taskRepository.findByUserId(new UserId(userId));
    }
}
