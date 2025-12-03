package com.empleados.patrones_diseno.infrastructure.mapper;

import com.empleados.patrones_diseno.domain.entities.Role;
import com.empleados.patrones_diseno.infrastructure.dto.RoleDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMap {

    RoleDTO toDTO(Role role);

    Role toEntity(RoleDTO roleDTO);
}