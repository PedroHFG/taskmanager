package com.dev.taskmanager.repositories;

import com.dev.taskmanager.entities.Task;
import com.dev.taskmanager.projections.TaskProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {


    List<Task> findByUserId(Long id);
}
