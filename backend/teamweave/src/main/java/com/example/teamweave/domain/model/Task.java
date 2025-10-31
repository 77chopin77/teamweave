package com.example.teamweave.domain.model;

import java.time.OffsetDateTime;
import java.util.Objects;

public class Task {
    private final TaskId id;
    private String title;
    private String description;
    private UserId assignee;
    private Status status;
    private OffsetDateTime dueDate;

    public enum Status { TODO, DOING, DONE }

    public Task(TaskId id, String title) {
        this.id = Objects.requireNonNull(id);
        this.title = Objects.requireNonNull(title);
        this.status = Status.TODO;
    }

    // --- Getter ---
    public TaskId getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public UserId getAssignee() { return assignee; }
    public Status getStatus() { return status; }
    public OffsetDateTime getDueDate() { return dueDate; }

    // --- Setter (必要最小限のみ公開) ---
    public void setDescription(String description) {
        this.description = description;
    }

    public void setAssignee(UserId assignee) {
        this.assignee = assignee;
    }

    public void setDueDate(OffsetDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    // --- 不変条件の制御もここで可能 ---
    public void markAsDone() {
        this.status = Status.DONE;
    }
}
