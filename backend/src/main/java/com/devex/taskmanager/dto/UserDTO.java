package com.devex.taskmanager.dto;

import com.devex.taskmanager.entities.User;
import org.springframework.security.core.GrantedAuthority;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class UserDTO {

    private String name;
    private String email;
    private Instant createdAt;
    private Instant updatedAt;
    private List<String> roles = new ArrayList<>();

    public UserDTO() {

    }

    public UserDTO(String name, String email, Instant createdAt, Instant updatedAt) {
        this.name = name;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UserDTO(User entity) {
        this.name = entity.getName();
        this.email = entity.getEmail();
        this.createdAt = entity.getCreatedAt();
        this.updatedAt = entity.getUpdatedAt();

        for (GrantedAuthority role : entity.getAuthorities()) {
            roles.add(role.getAuthority());
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<String> getRoles() {
        return roles;
    }
}
