package com.example.teamweave.presentation.controller;

import com.example.teamweave.application.dto.ApiResponse;
import com.example.teamweave.application.usecase.*;
import com.example.teamweave.domain.model.Task;
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
    public ResponseEntity<ApiResponse<List<Task>>> getTasks(
            @RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        return ResponseEntity.ok(ApiResponse.ok(getTasksService.getTasks(token)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> createTask(
            @RequestHeader("Authorization") String authHeader,
            @RequestBody Map<String, String> request
    ) {
        String token = authHeader.replace("Bearer ", "");
        createTaskService.createTask(token, request.get("title"));
        return ResponseEntity.ok(ApiResponse.message("Task created"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> updateTask(
            @RequestHeader("Authorization") String authHeader,
            @PathVariable String id,
            @RequestBody Map<String, String> request
    ) {
        String token = authHeader.replace("Bearer ", "");
        updateTaskService.updateTask(token, id, request.get("title"));
        return ResponseEntity.ok(ApiResponse.message("Task updated"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteTask(
            @RequestHeader("Authorization") String authHeader,
            @PathVariable String id
    ) {
        String token = authHeader.replace("Bearer ", "");
        deleteTaskService.deleteTask(token, id);
        return ResponseEntity.ok(ApiResponse.message("Task deleted"));
    }
}
