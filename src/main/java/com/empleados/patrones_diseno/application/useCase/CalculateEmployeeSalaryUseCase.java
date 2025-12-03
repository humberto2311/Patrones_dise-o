package com.empleados.patrones_diseno.application.useCase;


import com.empleados.patrones_diseno.domain.entities.Employee;
import com.empleados.patrones_diseno.domain.services.CalculoSalarioStrategy;
import com.empleados.patrones_diseno.domain.services.EmployeeGetService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CalculateEmployeeSalaryUseCase {

    private final EmployeeGetService employeeGetService;
    private final CalculoSalarioStrategy.SalaryCalculationPortService salaryCalculationPort;

    public CalculateEmployeeSalaryUseCase(EmployeeGetService employeeGetService,
                                          CalculoSalarioStrategy.SalaryCalculationPortService salaryCalculationPort) {
        this.employeeGetService = employeeGetService;
        this.salaryCalculationPort = salaryCalculationPort;
    }

    public BigDecimal execute(int employeeId, int months, boolean withBonus) {
        var employeeOpt = employeeGetService.getEmployeeById(employeeId);
        Employee employee = employeeOpt.orElseThrow(() -> new RuntimeException("Employee not found"));

        if (withBonus) {
            return salaryCalculationPort.calculateWithBonus(employee, months);
        } else {
            return salaryCalculationPort.calculateSalary(employee, months);
        }
    }

    public void registerExtraHours(int employeeId, int hours) {
        salaryCalculationPort.registerExtraHours(employeeId, hours);
    }
}
