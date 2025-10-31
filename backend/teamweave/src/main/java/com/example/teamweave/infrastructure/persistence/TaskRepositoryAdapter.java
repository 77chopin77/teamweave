package com.example.teamweave.infrastructure.persistence;

import com.example.teamweave.application.port.out.TaskRepositoryPort;
import com.example.teamweave.domain.model.*;
import com.example.teamweave.infrastructure.persistence.entity.TaskJpaEntity;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class TaskRepositoryAdapter implements TaskRepositoryPort {
private final TaskJpaRepository jpa;
public TaskRepositoryAdapter(TaskJpaRepository jpa){ this.jpa = jpa; }

@Override public Task save(Task t){
var e = new TaskJpaEntity();
e.setId(t.getId().value());
e.setTitle(t.getTitle());
e.setDescription(t.getDescription());
e.setAssigneeId(t.getAssignee()==null? null : t.getAssignee().value());
e.setStatus(t.getStatus().name());
e.setDueDate(t.getDueDate());
jpa.save(e);
return t; // 再読み込み省略
}
@Override public Optional<Task> findById(TaskId id){
return jpa.findById(id.value()).map(this::toDomain);
}
@Override public List<Task> findAllByAssignee(UserId userId){
return jpa.findByAssigneeId(userId.value()).stream().map(this::toDomain).toList();
}
private Task toDomain(TaskJpaEntity e){
var t = new Task(new TaskId(e.getId()), e.getTitle());
t.setDescription(e.getDescription());
if(e.getAssigneeId()!=null) t.setAssignee(new UserId(e.getAssigneeId()));
t.setDueDate(e.getDueDate());
t.setStatus(Task.Status.valueOf(e.getStatus()));
return t;
}
}