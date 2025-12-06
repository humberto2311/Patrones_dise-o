package com.empleados.patrones_diseno.infrastructure.adapter;

import com.empleados.patrones_diseno.domain.entities.Role;
import com.empleados.patrones_diseno.domain.services.RoleSaveService;
import com.empleados.patrones_diseno.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class RoleSaveAdapter implements RoleSaveService {
    private final RoleRepository roleRepository;
    @Override
    public Role saveRole(Role role) {
        return  roleRepository.save(role);
    }
}
