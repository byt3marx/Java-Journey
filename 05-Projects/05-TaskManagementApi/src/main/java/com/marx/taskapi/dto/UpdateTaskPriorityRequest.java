package com.marx.taskapi.dto;

import com.marx.taskapi.model.TaskPriority;
import jakarta.validation.constraints.NotNull;

public record UpdateTaskPriorityRequest(
        @NotNull(message = "Priority is required")
        TaskPriority priority) {

}
