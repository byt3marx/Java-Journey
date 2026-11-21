package com.marx.taskapi.dto;

import com.marx.taskapi.model.TaskStatus;
import jakarta.validation.constraints.NotNull;

public record UpdateTaskStatusRequest(
        @NotNull(message = "Status is required")
        TaskStatus status
) {
}
