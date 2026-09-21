package com.marx.taskapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 100)
    private String title;

    @Size(max = 1000)
    private String description;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TaskPriority priority;

    @NotNull
    private LocalDate dueDate;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    private void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    private void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    protected Task() {
    }

    public Task(String title,
                String description,
                TaskPriority priority,
                LocalDate dueDate) {

        validateTitle(title);
        validatePriority(priority);
        validateDueDate(dueDate);
        validateDescription(description);

        this.title = title;
        this.description = description;
        this.priority = priority;
        this.dueDate = dueDate;
        this.status = TaskStatus.TO_DO;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public TaskPriority getPriority() {
        return priority;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
//----------------------------------------------------------------------------

    public void setTitle(String title) {
        validateTitle(title);
        this.title = title;
    }

    public void setDescription(String description) {
        validateDescription(description);
        this.description = description;
    }

    public void setPriority(TaskPriority priority) {
        validatePriority(priority);
        this.priority = priority;
    }

    public void setDueDate(LocalDate dueDate) {
        validateDueDate(dueDate);
        this.dueDate = dueDate;
    }

    public void markInProgress() {
        this.status = TaskStatus.IN_PROGRESS;
    }

    public void markDone() {
        this.status = TaskStatus.DONE;
    }

    public void markToDo() {
        this.status = TaskStatus.TO_DO;
    }
//----------------------------------------------------------------------------

    private static void validateTitle(String title) {
        if (title == null || title.isBlank() || title.length() > 100) {
            throw new IllegalArgumentException(
                    "Title must not be blank and must be at most 100 characters."
            );
        }
    }

    private static void validatePriority(TaskPriority priority) {
        if (priority == null) {
            throw new IllegalArgumentException(
                    "Priority must not be null."
            );
        }
    }

    private static void validateDueDate(LocalDate dueDate) {
        if (dueDate == null) {
            throw new IllegalArgumentException(
                    "Due date must not be null."
            );
        }
    }

    private static void validateDescription(String description) {
        if (description != null && description.length() > 1000) {
            throw new IllegalArgumentException("Description must be at most 1000 characters.");
        }
    }
}
