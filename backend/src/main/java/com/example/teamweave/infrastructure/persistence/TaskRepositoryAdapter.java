package com.example.teamweave.infrastructure.persistence;

import com.example.teamweave.application.port.out.TaskRepositoryPort;
import com.example.teamweave.domain.model.*;
import com.example.teamweave.infrastructure.persistence.entity.TaskJpaEntity;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class TaskRepositoryAdapter implements TaskRepositoryPort {

    private final TaskJpaRepository jpa;

    public TaskRepositoryAdapter(TaskJpaRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public Task save(Task t) {
        var e = new TaskJpaEntity();
        e.setId(t.getId().value());
        e.setUserId(t.getUser() == null ? null : t.getUser().value());
        e.setAssigneeId(t.getAssignee() == null ? null : t.getAssignee().value());
        e.setTitle(t.getTitle());
        e.setDescription(t.getDescription());
        e.setStatus(t.getStatus().name());
        e.setDueDate(t.getDueDate());
        jpa.save(e);
        return t; // 再読込省略
    }

    @Override
    public Optional<Task> findById(TaskId id) {
        return jpa.findById(id.value()).map(this::toDomain);
    }

    @Override
    public List<Task> findAllByAssignee(UserId userId) {
        return jpa.findByAssigneeId(userId.value())
                .stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Task> findByIdAndUserId(UUID taskId, UUID userId) {
        return jpa.findById(taskId)
                .filter(e -> e.getUserId().equals(userId))
                .map(this::toDomain);
    }

    @Override
    public void deleteByIdAndUserId(UUID taskId, UUID userId) {
        jpa.findById(taskId)
                .filter(e -> e.getUserId().equals(userId))
                .ifPresent(jpa::delete);
    }

    /** ✅ findByUserId をUUID対応で実装 */
    public List<Task> findByUserId(UserId userId) {
        return jpa.findByUserId(userId.value())
                .stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    private Task toDomain(TaskJpaEntity e) {
        var t = new Task(new TaskId(e.getId()), e.getTitle());
        t.setDescription(e.getDescription());
        if (e.getUserId() != null) t.setUser(new UserId(e.getUserId()));
        if (e.getAssigneeId() != null) t.setAssignee(new UserId(e.getAssigneeId()));
        t.setDueDate(e.getDueDate());
        t.setStatus(Task.Status.valueOf(e.getStatus()));
        return t;
    }
}
