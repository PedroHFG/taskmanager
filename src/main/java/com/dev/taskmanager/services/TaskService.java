package com.dev.taskmanager.services;

import com.dev.taskmanager.dto.TaskDTO;
import com.dev.taskmanager.entities.Task;
import com.dev.taskmanager.entities.TaskStatus;
import com.dev.taskmanager.entities.User;
import com.dev.taskmanager.repositories.TaskRepository;
import com.dev.taskmanager.services.exceptions.DatabaseException;
import com.dev.taskmanager.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @Transactional(readOnly = true)
    public TaskDTO findById(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado"));
        authService.validateSelfOrAdmin(task.getUser().getId());
        return new TaskDTO(task);
    }

    @Transactional(readOnly = true)
    public List<TaskDTO> findAll() {
        User me = userService.authenticated();
        List<Task> result = taskRepository.findByUserId(me.getId());
        return result.stream().map(x -> new TaskDTO(x)).toList();
    }


    @Transactional
    public TaskDTO insert(TaskDTO dto) {
        Task entity = new Task();
        copyDtoToEntity(dto, entity);

        entity.setStatus(TaskStatus.PENDING);

        User user = userService.authenticated();
        entity.setUser(user);

        entity = taskRepository.save(entity);
        return new TaskDTO(entity);
    }

    @Transactional
    public TaskDTO update(Long id, TaskDTO dto) {
        try {
            Task entity = taskRepository.getReferenceById(id);
            authService.validateSelfOrAdmin(entity.getUser().getId());
            copyDtoToEntity(dto, entity);
            entity = taskRepository.save(entity);
            return new TaskDTO(entity);
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Recurso não encontrado!");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado"));
        authService.validateSelfOrAdmin(task.getUser().getId());

        try {
            taskRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }

    }

    private void copyDtoToEntity(TaskDTO dto, Task entity) {
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setStatus(dto.getStatus());
        entity.setDueDate(dto.getDueDate());
    }
}
