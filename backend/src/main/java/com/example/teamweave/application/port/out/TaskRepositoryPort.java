package com.example.teamweave.application.port.out;

import com.example.teamweave.domain.model.Task;
import com.example.teamweave.domain.model.TaskId;
import com.example.teamweave.domain.model.UserId;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskRepositoryPort {

    Task save(Task task);

    Optional<Task> findById(TaskId id);

    List<Task> findByUserId(UserId userId);

    List<Task> findAllByAssignee(UserId assigneeId);

    // ✅ 追加（DeleteTaskService用）
    void deleteByIdAndUserId(UUID taskId, UUID userId);

    // ✅ 追加（UpdateTaskService用）
    Optional<Task> findByIdAndUserId(UUID taskId, UUID userId);
}
