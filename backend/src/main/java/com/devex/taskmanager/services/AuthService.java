package com.devex.taskmanager.services;

import com.devex.taskmanager.entities.User;
import com.devex.taskmanager.services.exceptions.ForbiddenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserService service;

    public void validateSelfOrAdmin(Long userId) {
        User me = service.authenticated();

        if (!me.getId().equals(userId) && !me.hasRole("ROLE_ADMIN")) {
            throw new ForbiddenException("Acesso negado");
        }
    }
}
