package com.marx.taskapi.repository;

import com.marx.taskapi.model.Task;
import com.marx.taskapi.model.TaskPriority;
import com.marx.taskapi.model.TaskStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;

    @Test
    void findByStatusReturnOnlyTasksWithMatchingStatus() {
        Task toDoTask = new Task(
                "To-do task",
                "Should not be returned",
                TaskPriority.LOW,
                LocalDate.now().plusDays(1)
        );

        Task inProgressTask = new Task(
                "In-progress task",
                "Shold be returned",
                TaskPriority.HIGH,
                LocalDate.now().plusDays(2)
        );

        inProgressTask.setStatus(TaskStatus.IN_PROGRESS);

        taskRepository.saveAll(List.of(toDoTask, inProgressTask));

        List<Task> result = taskRepository.findByStatus(TaskStatus.IN_PROGRESS);

        assertEquals(1, result.size());
        assertEquals("In-progress task", result.get(0).getTitle());
        assertEquals(TaskStatus.IN_PROGRESS, result.get(0).getStatus());
    }

    @Test
    void findByPriorityReturnsOnlyTasksWithMatchingPriority() {
        Task lowPriorityTask = new Task(
                "Low-priority task",
                "Should not be returned",
                TaskPriority.LOW,
                LocalDate.now().plusDays(1)
        );

        Task highPriorityTask = new Task(
                "High-priority task",
                "Should be returned",
                TaskPriority.HIGH,
                LocalDate.now().plusDays(5)
        );

        taskRepository.saveAll(List.of(lowPriorityTask, highPriorityTask));

        List<Task> result = taskRepository.findByPriority(TaskPriority.HIGH);

        assertEquals(1, result.size());
        assertEquals("High-priority task", result.get(0).getTitle());
        assertEquals(TaskPriority.HIGH, result.get(0).getPriority());
    }

    @Test
    void findByDueDateReturnsOnlyTasksWithMatchingDueDate() {
        LocalDate targetDate = LocalDate.now().plusDays(3);

        Task matchingDueDateTask = new Task(
                "The correct date",
                "Should be returned",
                TaskPriority.MEDIUM,
                targetDate
        );

        Task nonMatchingDueDateTask = new Task(
                "The wrong date",
                "Should not be returned",
                TaskPriority.MEDIUM,
                targetDate.plusDays(2)
        );

        taskRepository.saveAll(List.of(matchingDueDateTask, nonMatchingDueDateTask));

        List<Task> result = taskRepository.findByDueDate(targetDate);

        assertEquals(1, result.size());
        assertEquals("The correct date", result.get(0).getTitle());
        assertEquals(targetDate, result.get(0).getDueDate());
    }
}
