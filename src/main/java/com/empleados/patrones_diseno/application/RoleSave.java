package com.empleados.patrones_diseno.application;

import com.empleados.patrones_diseno.domain.entities.Role;
import com.empleados.patrones_diseno.domain.services.RoleSaveService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RoleSave {

    private  final RoleSaveService roleSaveService;

    public Role saveRole(Role role){
        return roleSaveService.saveRole(role);
    }
}
