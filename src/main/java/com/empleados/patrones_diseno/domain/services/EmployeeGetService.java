package com.empleados.patrones_diseno.domain.services;

import com.empleados.patrones_diseno.domain.entities.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeGetService {

    Optional<Employee> getEmployeeById(int id);

    List<Employee> findAllEmployees();
}
