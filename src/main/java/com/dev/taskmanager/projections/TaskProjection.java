package com.dev.taskmanager.projections;

import java.time.LocalDate;

public interface TaskProjection {

    Long getId();
    String getTitle();
    String getDescription();
    LocalDate getDueDate();
    Integer getStatus();
    Integer getUserId();
}
