package com.marx.taskapi.repository;

import com.marx.taskapi.model.Task;
import com.marx.taskapi.model.TaskPriority;
import com.marx.taskapi.model.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByStatus(TaskStatus status);
    List<Task> findByPriority(TaskPriority priority);
}
