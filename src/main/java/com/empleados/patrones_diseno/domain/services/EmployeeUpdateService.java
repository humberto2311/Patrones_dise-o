package com.empleados.patrones_diseno.domain.services;



import com.empleados.patrones_diseno.domain.entities.Employee;

import java.util.Optional;

public interface EmployeeUpdateService {
    Optional<Employee> getEmployeeById(int employeeId);

    Employee updateEmployee(Employee employee);
}
