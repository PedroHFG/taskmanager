package com.dev.taskmanager.repositories;

import com.dev.taskmanager.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
