package com.dev.taskmanager.services;

import com.dev.taskmanager.dto.UserDTO;
import com.dev.taskmanager.dto.UserMinDTO;
import com.dev.taskmanager.entities.Role;
import com.dev.taskmanager.entities.User;
import com.dev.taskmanager.projections.UserDetailsProjection;
import com.dev.taskmanager.projections.UserProjection;
import com.dev.taskmanager.repositories.RoleRepository;
import com.dev.taskmanager.repositories.UserRepository;
import com.dev.taskmanager.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Transactional(readOnly = true)
    public UserDTO findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado"));
        return new UserDTO(user);
    }

    @Transactional(readOnly = true)
    public List<UserDTO> findAll() {
        List<UserProjection> result = userRepository.searchUserAndRoles();

        // Agrupando os resultados por ID do usuário
        Map<Long, UserDTO> userMap = new HashMap<>();

        for (UserProjection projection : result) {
            Long userId = projection.getId();
            // Se o usuário ainda não foi adicionado, cria um novo UserDTO
            if (!userMap.containsKey(userId)) {
                UserDTO userDTO = new UserDTO();
                userDTO.setId(projection.getId());
                userDTO.setEmail(projection.getEmail());
                userDTO.setName(projection.getName());
                userDTO.setPassword(projection.getPassword());

                userMap.put(userId, userDTO);
            }
            // Adiciona a role ao UserDTO existente
            userMap.get(userId).addRole(projection.getAuthority());
        }
        return new ArrayList<>(userMap.values());
    }


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
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<UserDetailsProjection> result = userRepository.searchUserAndRolesByEmail(username);

        if (result.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        User user = new User();
        user.setEmail(username);
        user.setPassword(result.get(0).getPassword());

        for (UserDetailsProjection projection : result) {
            user.addRole(new Role(projection.getRoleId(), projection.getAuthority()));
        }
        return user;
    }

    protected User authenticated() {

        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Jwt jwtPrincipal = (Jwt) authentication.getPrincipal();
            String username = jwtPrincipal.getClaim("username");

            User user = userRepository.findByEmail(username).get();

            return user;

        }
        catch (Exception e) {
            throw new UsernameNotFoundException("Email not found");
        }
    }
}
