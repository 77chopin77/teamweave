package com.example.teamweave.application.port.out;
import com.example.teamweave.domain.model.*;
import java.util.*;

public interface TaskRepositoryPort {
Task save(Task task);
Optional<Task> findById(TaskId id);
List<Task> findAllByAssignee(UserId userId);
}