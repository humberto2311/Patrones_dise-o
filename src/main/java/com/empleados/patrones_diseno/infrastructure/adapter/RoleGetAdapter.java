package com.empleados.patrones_diseno.infrastructure.adapter;

import com.empleados.patrones_diseno.domain.entities.Employee;
import com.empleados.patrones_diseno.domain.entities.Role;
import com.empleados.patrones_diseno.domain.services.RoleGetService;
import com.empleados.patrones_diseno.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class RoleGetAdapter implements RoleGetService {
    private final RoleRepository roleRepository;
    @Override
    public Optional<Role> getRoleById(int roleId) {
        return roleRepository.findById(roleId);
    }
}