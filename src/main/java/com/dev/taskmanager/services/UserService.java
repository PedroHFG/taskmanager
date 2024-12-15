package com.dev.taskmanager.services;

import com.dev.taskmanager.dto.UserDTO;
import com.dev.taskmanager.entities.Role;
import com.dev.taskmanager.entities.User;
import com.dev.taskmanager.repositories.RoleRepository;
import com.dev.taskmanager.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Transactional
    public UserDTO insert(UserDTO dto) {
        // Criar um novo objeto User a partir do DTO
        User entity = new User();
        copyDtoToEntity(dto, entity);

        // Buscar o papel ROLE_USER no banco de dados
        Role roleUser = roleRepository.findByAuthority("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Role ROLE_USER não encontrada"));

        // Associar o papel ROLE_USER ao usuário
        entity.addRole(roleUser);

        // Salvar o usuário no banco de dados e retorna o DTO com o usuário cadastrado
        entity = userRepository.save(entity);
        return new UserDTO(entity);
    }

    private void copyDtoToEntity(UserDTO dto, User entity) {
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
    }
}
