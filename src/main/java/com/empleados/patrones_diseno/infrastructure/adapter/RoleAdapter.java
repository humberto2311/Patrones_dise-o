package com.empleados.patrones_diseno.infrastructure.adapter;

import com.empleados.patrones_diseno.domain.entities.Role;
import com.empleados.patrones_diseno.domain.services.RoleGetService;
import com.empleados.patrones_diseno.domain.services.RoleSaveService;
import com.empleados.patrones_diseno.domain.services.RoleUpdateService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class RoleAdapter  {
    
    private final RoleGetService roleGetService;
    private final RoleSaveService roleSaveService;
    private final RoleUpdateService roleUpdateService;
    

    public Role getRoleById(int roleId) {
        return roleGetService.getRoleById(roleId);
    }

    public Role saveRole(Role role) {
        return roleSaveService.saveRole(role);
    }
    

    public Optional<Role> getRoleByIdForUpdate(int roleId) {
        return roleUpdateService.getRoleById(roleId);
    }
}