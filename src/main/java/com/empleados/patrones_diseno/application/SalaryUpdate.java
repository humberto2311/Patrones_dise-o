package com.empleados.patrones_diseno.application;

import com.empleados.patrones_diseno.domain.entities.Employee;
import com.empleados.patrones_diseno.domain.services.EmployeeUpdateService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class SalaryUpdate {
    private final EmployeeUpdateService employeeUpdateService;

    public Optional<Employee> updateSalary(int employeeId) {
        return employeeUpdateService.getEmployeeById(employeeId);
    }
}
