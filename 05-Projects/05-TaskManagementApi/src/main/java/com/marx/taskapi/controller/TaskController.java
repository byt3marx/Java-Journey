package com.marx.taskapi.controller;

import com.marx.taskapi.dto.UpdateTaskDescriptionRequest;
import com.marx.taskapi.model.Task;
import com.marx.taskapi.service.TaskService;
import com.marx.taskapi.dto.CreateTaskRequest;
import com.marx.taskapi.dto.UpdateTaskTitleRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")

public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id)
                .map(task -> ResponseEntity.ok(task))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Task> createTask(
            @RequestBody CreateTaskRequest request) {
        Task createdTask = taskService.createTask(
                request.title(),
                request.description(),
                request.priority(),
                request.dueDate()
        );

        return ResponseEntity.status(201).body(createdTask);
    }

    @PatchMapping("/{id}/title")
    public ResponseEntity<Task> updateTaskTitle(
            @PathVariable Long id,
            @RequestBody UpdateTaskTitleRequest request) {

        return taskService.updateTaskTitle(id, request.title())
                .map(task -> ResponseEntity.ok(task))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/description")
    public ResponseEntity<Task> updateTaskDescription(
            @PathVariable Long id,
            @RequestBody UpdateTaskDescriptionRequest request) {
        return taskService.updateTaskDescription(id, request.description())
                .map(task -> ResponseEntity.ok(task))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {

        if (taskService.deleteTask(id).isPresent()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}
