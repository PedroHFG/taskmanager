package com.dev.taskmanager.repositories;

import com.dev.taskmanager.entities.Role;
import com.dev.taskmanager.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
}
