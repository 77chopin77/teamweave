package com.example.teamweave.application.port.out;

import com.example.teamweave.domain.model.Task;
import com.example.teamweave.domain.model.TaskId;
import com.example.teamweave.domain.model.UserId;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/* タスクを保存・取得・削除するための「出力ポート（Port out）」インターフェース */
public interface TaskRepositoryPort {

    // タスクを保存する（新規 or 更新）
    Task save(Task task);

    // タスクIDで1件取得
    Optional<Task> findById(TaskId id);

    // ユーザーIDに紐づくタスクを全件取得
    List<Task> findByUserId(UserId userId);

    // 担当者（assignee）に割り当てられたタスクを取得
    List<Task> findAllByAssignee(UserId assigneeId);

    // 特定ユーザーのタスクを削除
    void deleteByIdAndUserId(UUID taskId, UUID userId);

    // 特定ユーザーの特定タスクを取得（UpdateTaskService用）
    Optional<Task> findByIdAndUserId(UUID taskId, UUID userId);
}
