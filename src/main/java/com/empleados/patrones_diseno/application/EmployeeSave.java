package com.empleados.patrones_diseno.application;

import com.empleados.patrones_diseno.domain.entities.Employee;
import com.empleados.patrones_diseno.domain.services.EmployeeSaveService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class EmployeeSave {
    private final EmployeeSaveService employeeSaveService;

    public Employee saveEmployee(Employee employee){
        return employeeSaveService.saveEmployee(employee);
    }
}
