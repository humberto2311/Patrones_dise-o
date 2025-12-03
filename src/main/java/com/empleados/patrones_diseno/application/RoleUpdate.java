package com.empleados.patrones_diseno.application;

import com.empleados.patrones_diseno.domain.entities.Role;
import com.empleados.patrones_diseno.domain.services.RoleUpdateService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class RoleUpdate {
    private final RoleUpdateService  roleUpdateService;

    public Optional<Role> updateRole(int role) {
        return roleUpdateService.getRoleById(role);
    }

}
