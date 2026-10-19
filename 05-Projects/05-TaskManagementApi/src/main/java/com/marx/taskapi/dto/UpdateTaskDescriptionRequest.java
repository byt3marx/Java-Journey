package com.marx.taskapi.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateTaskDescriptionRequest(
        @NotNull(message = "Description is required")
        @Size(max = 1000, message = "Description cannot exceed 1000 characters")
        String description) {
}
