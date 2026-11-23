package com.marx.taskapi.service;

import com.marx.taskapi.model.Task;
import com.marx.taskapi.model.TaskPriority;
import com.marx.taskapi.model.TaskStatus;
import com.marx.taskapi.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TaskServiceTest {

    private TaskRepository taskRepository;
    private TaskService taskService;

    @BeforeEach
    void setUp() {
        taskRepository = mock(TaskRepository.class);
        taskService = new TaskService(taskRepository);
    }

    @Test
    void getAllTasksReturnsTasksFromRepository() {
        Task task = new Task(
                "Learn testing",
                "Write the first service test",
                TaskPriority.HIGH,
                LocalDate.now().plusDays(1)
        );

        when(taskRepository.findAll()).thenReturn(List.of(task));

        List<Task> result = taskService.getAllTasks();

        assertEquals(1, result.size());
        assertEquals(task, result.get(0));
        verify(taskRepository).findAll();
    }

    @Test
    void getTaskByIdReturnsTaskWhenTaskExists() {
        Task task = new Task(
                "Learn Optional testing",
                "Test an existing task",
                TaskPriority.MEDIUM,
                LocalDate.now().plusDays(1)
        );

        when(taskRepository.findById(1L))
                .thenReturn(Optional.of(task));

        Optional<Task> result = taskService.getTaskById(1L);

        assertTrue(result.isPresent());
        assertSame(task, result.get());
        verify(taskRepository).findById(1L);
    }

    @Test
    void getTaskByIdReturnsEmptyWhenTaskDoesNotExist() {
        when(taskRepository.findById(999L))
                .thenReturn(Optional.empty());

        Optional<Task> result = taskService.getTaskById(999L);

        assertTrue(result.isEmpty());
        verify(taskRepository).findById(999L);
    }

    @Test
    void updateTaskStatusUpdatesAndSavesTaskWhenTaskExists() {
        Task task = new Task(
                "Test status update",
                "Change TO_DO to IN_PROGRESS",
                TaskPriority.HIGH,
                LocalDate.now().plusDays(1)
        );

        when(taskRepository.findById(1L))
                .thenReturn(Optional.of(task));

        when(taskRepository.save(task))
                .thenReturn(task);

        Optional<Task> result = taskService.updateTaskStatus(1L, TaskStatus.IN_PROGRESS);

        assertTrue(result.isPresent());
        assertEquals(TaskStatus.IN_PROGRESS, result.get().getStatus());

        verify(taskRepository).findById(1L);
        verify(taskRepository).save(task);
    }

    @Test
    void updateTaskStatusDoesNotSaveWhenTaskDoesNotExist() {
        when(taskRepository.findById(999L))
                .thenReturn(Optional.empty());

        Optional<Task> result = taskService.updateTaskStatus(999L, TaskStatus.DONE);

        verify(taskRepository).findById(999L);
        verify(taskRepository, never()).save(any(Task.class));
    }
}
