package com.empleados.patrones_diseno.application;

import com.empleados.patrones_diseno.domain.entities.Employee;
import com.empleados.patrones_diseno.domain.services.EmployeeGetService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeGet {
    private final EmployeeGetService employeeGetService;

    public Optional<Employee>  getEmployeeById(int id) {
        return employeeGetService.getEmployeeById(id);
    }


}
