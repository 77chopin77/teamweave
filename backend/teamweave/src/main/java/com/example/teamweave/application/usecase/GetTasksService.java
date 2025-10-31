package com.example.teamweave.application.usecase;

import com.example.teamweave.infrastructure.persistence.TaskJpaRepository;
import com.example.teamweave.infrastructure.security.JwtProvider;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class GetTasksService {

    private final TaskJpaRepository tasks;
    private final JwtProvider jwt;

    public GetTasksService(TaskJpaRepository tasks, JwtProvider jwt) {
        this.tasks = tasks;
        this.jwt = jwt;
    }

    public List<Map<String, Object>> getTasks(String token) {
        Long userId = Long.parseLong(jwt.validateAndGetUserId(token));
        return tasks.findByUserId(userId)
                .stream()
                .map(t -> Map.<String, Object>of(  // ✅ 明示的に型を指定
                        "id", t.getId(),
                        "title", t.getTitle()
                ))
                .collect(Collectors.toList());
    }
}
