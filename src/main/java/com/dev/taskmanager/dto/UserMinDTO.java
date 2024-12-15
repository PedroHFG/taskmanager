package com.dev.taskmanager.dto;

import com.dev.taskmanager.entities.User;

public class UserMinDTO {

    private Long id;
    private String name;
    private String email;

    public UserMinDTO() {

    }

    public UserMinDTO(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public UserMinDTO(User entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.email = entity.getEmail();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
