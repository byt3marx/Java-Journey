package com.marx.taskapi.controller;

import com.marx.taskapi.dto.*;
import com.marx.taskapi.model.Task;
import com.marx.taskapi.model.TaskPriority;
import com.marx.taskapi.model.TaskStatus;
import com.marx.taskapi.service.TaskService;

import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.time.LocalDate;
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
            @Valid @RequestBody CreateTaskRequest request) {
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
            @Valid @RequestBody UpdateTaskTitleRequest request) {

        return taskService.updateTaskTitle(id, request.title())
                .map(task -> ResponseEntity.ok(task))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/description")
    public ResponseEntity<Task> updateTaskDescription(
            @PathVariable Long id,
            @Valid @RequestBody UpdateTaskDescriptionRequest request) {

        return taskService.updateTaskDescription(id, request.description())
                .map(task -> ResponseEntity.ok(task))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/priority")
    public ResponseEntity<Task> updateTaskPriority(
            @PathVariable Long id,
            @Valid @RequestBody UpdateTaskPriorityRequest request) {

        return taskService.updateTaskPriority(id, request.priority())
                .map(task -> ResponseEntity.ok(task))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/due-date")
    public ResponseEntity<Task> updateTaskDueDate(
            @PathVariable Long id,
            @Valid @RequestBody UpdateTaskDueDateRequest request) {

        return taskService.updateTaskDueDate(id, request.dueDate())
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

    @PatchMapping("/{id}/status")
    public ResponseEntity<Task> updateTaskStatus(
            @PathVariable Long id,
            @Valid @RequestBody UpdateTaskStatusRequest request) {

        return taskService.updateTaskStatus(id, request.status())
                .map(task -> ResponseEntity.ok(task))
                .orElseGet(() -> ResponseEntity.notFound().build());
        }

    @GetMapping("/status/{status}")
    public List<Task> getTasksByStatus(
            @PathVariable TaskStatus status) {

        return taskService.getTasksByStatus(status);
    }

    @GetMapping("/priority/{priority}")
    public List<Task> getTasksByPriority(
            @PathVariable TaskPriority priority) {

        return taskService.getTasksByPriority(priority);
    }

    @GetMapping("/due-date/{dueDate}")
    public List<Task> getTaskByDueDate(
            @PathVariable
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate dueDate) {

        return taskService.getTasksByDueDate(dueDate);
    }

}
