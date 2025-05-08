package com.devex.taskmanager.services;

import com.devex.taskmanager.dto.TaskDTO;
import com.devex.taskmanager.entities.Task;
import com.devex.taskmanager.entities.User;
import com.devex.taskmanager.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserService userService;

    @Transactional
    public TaskDTO insertNewTask(TaskDTO dto) {
        Task task = new Task();
        copyDtoToEntity(dto, task);

        User user = userService.authenticated();
        task.setUser(user);
        task = taskRepository.save(task);

        return new TaskDTO(task);
    }

    private void copyDtoToEntity(TaskDTO dto, Task task) {
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setDeadline(dto.getDeadline());
        task.setPriority(dto.getPriority());
        task.setStatus(dto.getStatus());
    }
}
