package com.empleados.patrones_diseno.domain.services;

import com.empleados.patrones_diseno.domain.entities.Role;

import java.util.Optional;

public interface RoleGetService {

    Role getRoleById(int roleId);
}
