package com.example.teamweave.presentation.controller;

import com.example.teamweave.application.usecase.CreateTaskService;
import com.example.teamweave.application.usecase.GetTasksService;
import com.example.teamweave.application.usecase.UpdateTaskService;
import com.example.teamweave.application.usecase.DeleteTaskService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final GetTasksService getTasksService;
    private final CreateTaskService createTaskService;
    private final UpdateTaskService updateTaskService;
    private final DeleteTaskService deleteTaskService;

    public TaskController(GetTasksService getTasksService,
                          CreateTaskService createTaskService,
                          UpdateTaskService updateTaskService,
                          DeleteTaskService deleteTaskService) {
        this.getTasksService = getTasksService;
        this.createTaskService = createTaskService;
        this.updateTaskService = updateTaskService;
        this.deleteTaskService = deleteTaskService;
    }

    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> getTasks( // ✅ Object に修正
            @RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        return ResponseEntity.ok(getTasksService.getTasks(token));
    }

    @PostMapping
    public ResponseEntity<String> createTask(
            @RequestHeader("Authorization") String authHeader,
            @RequestBody Map<String, String> request
    ) {
        String token = authHeader.replace("Bearer ", "");
        String title = request.get("title");
        createTaskService.createTask(token, title);
        return ResponseEntity.ok("Task created");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateTask(
            @RequestHeader("Authorization") String authHeader,
            @PathVariable String id,
            @RequestBody Map<String, String> request
    ) {
        String token = authHeader.replace("Bearer ", "");
        String title = request.get("title");
        updateTaskService.updateTask(token, id, title);
        return ResponseEntity.ok("Task updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(
            @RequestHeader("Authorization") String authHeader,
            @PathVariable String id
    ) {
        String token = authHeader.replace("Bearer ", "");
        deleteTaskService.deleteTask(token, id);
        return ResponseEntity.ok("Task deleted");
    }
}
