package com.empleados.patrones_diseno.repository;

import com.empleados.patrones_diseno.domain.entities.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
}
