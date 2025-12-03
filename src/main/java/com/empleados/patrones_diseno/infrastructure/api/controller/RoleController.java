// java
package com.empleados.patrones_diseno.infrastructure.api.controller;

import com.empleados.patrones_diseno.application.RoleGet;
import com.empleados.patrones_diseno.application.RoleSave;
import com.empleados.patrones_diseno.application.RoleUpdate;
import com.empleados.patrones_diseno.domain.entities.Role;
import com.empleados.patrones_diseno.infrastructure.dto.RoleDTO;
import com.empleados.patrones_diseno.infrastructure.mapper.RoleMap;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/roles")
public class RoleController {
    private final RoleSave roleSave;
    private final RoleGet roleGet;
    private final RoleUpdate roleUpdate;
    private final RoleMap roleMap;



    @GetMapping
    public ResponseEntity<RoleDTO> getRole(@RequestParam Integer id) {
        Role role = roleGet.getRoleById(id);
        return new ResponseEntity<>(roleMap.toDTO(role), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Role> saveRole(@RequestBody Role role) {
        return new ResponseEntity<>(roleSave.saveRole(role), HttpStatus.CREATED);
    }
}
