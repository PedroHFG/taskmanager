package com.devex.taskmanager.repositories;

import com.devex.taskmanager.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
