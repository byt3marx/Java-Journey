package com.marx.taskapi.controller;

import com.marx.taskapi.model.Task;
import com.marx.taskapi.model.TaskPriority;
import com.marx.taskapi.service.TaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.validateMockitoUsage;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TaskController.class)
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TaskService taskService;

    @Test
    void getAllTasksReturnsOkAndTasksAsJson() throws Exception {
        Task task = new Task(
                "Controller test",
                "Test GET endpoint",
                TaskPriority.HIGH,
                LocalDate.now().plusDays(1)
        );

        when(taskService.getAllTasks())
                .thenReturn(List.of(task));

        mockMvc.perform(get("/api/tasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title")
                        .value("Controller test"))
                .andExpect(jsonPath("$[0].description")
                        .value("Test GET endpoint"))
                .andExpect(jsonPath("$[0].priority")
                        .value("HIGH"))
                .andExpect(jsonPath("$[0].status")
                        .value("TO_DO"));
    }

    @Test
    void getTaskByIdReturnsOkWhenTaskExists() throws Exception {
        Task task = new Task(
                "Existing task",
                "Task found by ID",
                TaskPriority.MEDIUM,
                LocalDate.now().plusDays(1)
        );

        when(taskService.getTaskById(1L))
                .thenReturn(Optional.of(task));

        mockMvc.perform(get("/api/tasks/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title")
                        .value("Existing task"))
                .andExpect(jsonPath("$.priority")
                        .value("MEDIUM"));
    }

    @Test
    void getTaskByIdReturnsNotFoundWhenTaskDoesNotExist() throws Exception {
        when(taskService.getTaskById(999L))
                .thenReturn(Optional.empty());

        mockMvc.perform(get("/api/tasks/999"))
                .andExpect(status().isNotFound());
    }

}
