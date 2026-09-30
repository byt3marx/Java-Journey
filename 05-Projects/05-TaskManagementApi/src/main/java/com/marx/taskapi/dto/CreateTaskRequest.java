package com.marx.taskapi.dto;

import com.marx.taskapi.model.TaskPriority;

import java.time.LocalDate;

public record CreateTaskRequest(
        String title,
        String description,
        TaskPriority priority,
        LocalDate dueDate
) {
}
