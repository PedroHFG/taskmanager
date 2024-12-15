package com.dev.taskmanager.dto;

import com.dev.taskmanager.entities.Task;
import com.dev.taskmanager.entities.TaskStatus;

import java.time.LocalDate;

public class TaskDTO {

    private Long id;
    private UserMinDTO user;
    private String title;
    private String description;
    private TaskStatus status;
    private LocalDate dueDate;

    public TaskDTO () {

    }

    public TaskDTO(Long id, UserMinDTO user, String title, String description, TaskStatus status, LocalDate dueDate) {
        this.id = id;
        this.user = user;
        this.title = title;
        this.description = description;
        this.status = status;
        this.dueDate = dueDate;
    }

    public TaskDTO(Task entity) {
        this.id = entity.getId();
        this.user = new UserMinDTO(entity.getUser());
        this.title = entity.getTitle();
        this.description = entity.getDescription();
        this.status = entity.getStatus();
        this.dueDate = entity.getDueDate();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public UserMinDTO getUser() {
        return user;
    }

    public void setUser(UserMinDTO user) {
        this.user = user;
    }
}
