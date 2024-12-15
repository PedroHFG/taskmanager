package com.dev.taskmanager.services;

import com.dev.taskmanager.dto.TaskDTO;
import com.dev.taskmanager.entities.Task;
import com.dev.taskmanager.entities.TaskStatus;
import com.dev.taskmanager.entities.User;
import com.dev.taskmanager.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserService userService;

    public TaskDTO insert(TaskDTO dto) {
        Task entity = new Task();
        copyDtoToEntity(dto, entity);

        User user = userService.authenticated();
        entity.setUser(user);

        entity = taskRepository.save(entity);
        return new TaskDTO(entity);
    }

    private void copyDtoToEntity(TaskDTO dto, Task entity) {
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setStatus(TaskStatus.PENDING);
        entity.setDueDate(dto.getDueDate());
    }
}
