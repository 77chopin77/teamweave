package com.example.teamweave.domain.model;

import java.time.OffsetDateTime;


public class Task {
    private TaskId id;
    private UserId user;       // 所有者
    private UserId assignee;   // 担当者
    private String title;
    private String description;
    private Status status;
    private OffsetDateTime dueDate;

    public enum Status { TODO, IN_PROGRESS, DONE }

    public Task(TaskId id, String title) {
        this.id = id;
        this.title = title;
        this.status = Status.TODO;
    }

    // --- Getters / Setters ---
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
