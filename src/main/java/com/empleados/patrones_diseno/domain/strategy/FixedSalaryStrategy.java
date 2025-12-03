package com.empleados.patrones_diseno.domain.strategy;

import com.empleados.patrones_diseno.domain.entities.Employee;
import com.empleados.patrones_diseno.domain.services.SalaryUpdateService;

import java.math.BigDecimal;

public class FixedSalaryStrategy implements SalaryUpdateService.SalaryStrategyService {
    
    private static final BigDecimal EXTRA_HOUR_RATE = new BigDecimal("35000");
    
    @Override
    public BigDecimal calculate(Employee employee, int months) {
        BigDecimal monthlySalary = employee.getCurrentSalary();
        BigDecimal totalSalary = monthlySalary.multiply(BigDecimal.valueOf(months));
        
        // Calcular horas extras
        if (employee.getExtraHours() > 0) {
            BigDecimal extraHoursPayment = EXTRA_HOUR_RATE
                .multiply(BigDecimal.valueOf(employee.getExtraHours()))
                .multiply(BigDecimal.valueOf(months));
            totalSalary = totalSalary.add(extraHoursPayment);
        }
        
        return totalSalary;
    }
}