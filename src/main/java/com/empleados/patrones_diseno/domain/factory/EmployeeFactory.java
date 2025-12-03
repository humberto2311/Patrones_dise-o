package com.empleados.patrones_diseno.domain.factory;

import com.empleados.patrones_diseno.domain.entities.Employee;
import com.empleados.patrones_diseno.domain.entities.Role;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class EmployeeFactory {
    
    private static final BigDecimal SALARIO_BASE_FIJO = new BigDecimal("2500000");
    private static final BigDecimal SALARIO_BASE_POR_HORAS = new BigDecimal("15000"); // por hora
    private static final BigDecimal SALARIO_BASE_COMISION = new BigDecimal("1500000");
    
    public static Employee createEmployee(EmployeeType type, String firstName, String lastName, Role role) {
        Employee employee = new Employee();
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setHireDate(LocalDateTime.now());
        employee.setCurrentRole(role);
        employee.setEmployeeType(type.toString());
        
        switch (type) {
            case FIJO:
                employee.setCurrentSalary(SALARIO_BASE_FIJO);
                break;
            case POR_HORAS:
                employee.setCurrentSalary(SALARIO_BASE_POR_HORAS);
                break;
            case COMISION:
                employee.setCurrentSalary(SALARIO_BASE_COMISION);
                break;
        }
        
        return employee;
    }
}