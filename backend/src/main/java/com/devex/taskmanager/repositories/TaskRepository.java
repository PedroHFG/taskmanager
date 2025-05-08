package com.devex.taskmanager.repositories;

import com.devex.taskmanager.entities.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @EntityGraph(attributePaths = "user")
    Page<Task> findAll(Pageable pageable);

    @EntityGraph(attributePaths = "user")
    Page<Task> findByUserId(Long userId, Pageable pageable);
}
