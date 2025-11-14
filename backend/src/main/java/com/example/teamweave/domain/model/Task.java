package com.example.teamweave.domain.model;

import java.time.OffsetDateTime;

/* タスクを表すドメインモデル（ビジネス上の中心的な概念） */
public class Task {
    private TaskId id;              // タスクID
    private UserId user;            // 所有者
    private UserId assignee;        // 担当者
    private String title;           // タイトル
    private String description;     // 詳細説明
    private Status status;          // 状態
    private OffsetDateTime dueDate; // 期限日

    // タスクの状態を定義する列挙型
    public enum Status { TODO, IN_PROGRESS, DONE }

    // コンストラクタ
    public Task(TaskId id, String title) {
        this.id = id;
        this.title = title;
        this.status = Status.TODO;
    }

    // ゲッターとセッター
    public TaskId getId() { return id; }
    public void setId(TaskId id) { this.id = id; }

    public UserId getUser() { return user; }
    public void setUser(UserId user) { this.user = user; }

    public UserId getAssignee() { return assignee; }
    public void setAssignee(UserId assignee) { this.assignee = assignee; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    public OffsetDateTime getDueDate() { return dueDate; }
    public void setDueDate(OffsetDateTime dueDate) { this.dueDate = dueDate; }
}
