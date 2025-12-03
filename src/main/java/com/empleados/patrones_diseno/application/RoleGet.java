package com.empleados.patrones_diseno.application;


import com.empleados.patrones_diseno.domain.entities.Role;
import com.empleados.patrones_diseno.domain.services.RoleGetService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RoleGet {
    private final RoleGetService roleGetService;

    public Role getRoleById(int id) {
        return roleGetService.getRoleById(id);
    }
}
