package com.devex.taskmanager.services;

import com.devex.taskmanager.dto.TaskDTO;
import com.devex.taskmanager.entities.Task;
import com.devex.taskmanager.entities.User;
import com.devex.taskmanager.repositories.TaskRepository;
import com.devex.taskmanager.services.exceptions.DatabaseException;
import com.devex.taskmanager.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @Transactional
    public TaskDTO insertNewTask(TaskDTO dto) {
        Task task = new Task();
        copyDtoToEntity(dto, task);

        User user = userService.authenticated();
        task.setUser(user);
        task = taskRepository.save(task);

        return new TaskDTO(task);
    }

    @Transactional
    public TaskDTO updateTask(Long id, TaskDTO dto) {
        try {
            Task task = taskRepository.getReferenceById(id);
            authService.validateSelfOrAdmin(task.getUser().getId());
            copyDtoToEntity(dto, task);
            task = taskRepository.save(task);
            return new TaskDTO(task);
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Tarefa não encontrada");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void deleteTask(Long id) {

        Task task = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada"));
        authService.validateSelfOrAdmin(task.getUser().getId());

        try {
            taskRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Erro de integridade referencial");
        }
    }

    @Transactional(readOnly = true)
    public TaskDTO findTaskById(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada"));
        authService.validateSelfOrAdmin(task.getUser().getId());
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
