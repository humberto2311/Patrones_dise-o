package com.empleados.patrones_diseno.domain.strategy;

import com.empleados.patrones_diseno.domain.entities.Employee;
import com.empleados.patrones_diseno.domain.services.SalaryUpdateService;

import java.math.BigDecimal;

public class HourlySalaryStrategy implements SalaryUpdateService.SalaryStrategyService {
    
    private static final BigDecimal MONTHLY_HOURS = new BigDecimal("160"); // 40h/semana * 4
    private static final BigDecimal EXTRA_HOUR_RATE = new BigDecimal("35000");
    
    @Override
    public BigDecimal calculate(Employee employee, int months) {
        // Salario por horas normales
        BigDecimal hourlyRate = employee.getCurrentSalary();
        BigDecimal regularSalary = hourlyRate
            .multiply(MONTHLY_HOURS)
            .multiply(BigDecimal.valueOf(months));
        
        // Calcular horas extras
        BigDecimal extraSalary = BigDecimal.ZERO;
        if (employee.getExtraHours() > 0) {
            extraSalary = EXTRA_HOUR_RATE
                .multiply(BigDecimal.valueOf(employee.getExtraHours()))
                .multiply(BigDecimal.valueOf(months));
        }
        
        return regularSalary.add(extraSalary);
    }
}