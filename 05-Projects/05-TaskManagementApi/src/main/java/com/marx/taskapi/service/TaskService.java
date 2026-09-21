package com.marx.taskapi.service;

import com.marx.taskapi.model.Task;
import com.marx.taskapi.model.TaskPriority;
import com.marx.taskapi.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task createTask(String title,
                           String description,
                           TaskPriority priority,
                           LocalDate dueDate) {

        Task task = new Task(title, description, priority, dueDate);
        return taskRepository.save(task);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    public Optional<Task> updateTaskTitle(Long id, String title) {
        return taskRepository.findById(id)
                .map(task -> {
                    task.setTitle(title);
                    return taskRepository.save(task);
                });
    }

    public Optional<Task> updateTaskDescription(Long id, String description) {
        return taskRepository.findById(id)
                .map(task -> {
                    task.setDescription(description);
                    return taskRepository.save(task);
                });
    }

    public Optional<Task> deleteTask(Long id) {
        return taskRepository.findById(id)
                .map(task -> {
                    taskRepository.delete(task);
                    return task;
                });
    }
}
