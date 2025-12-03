package com.empleados.patrones_diseno.domain.strategy;

import com.empleados.patrones_diseno.domain.entities.Employee;
import com.empleados.patrones_diseno.domain.services.SalaryUpdateService;

import java.math.BigDecimal;

public class CommissionSalaryStrategy implements SalaryUpdateService.SalaryStrategyService {
    
    private static final BigDecimal COMMISSION_RATE = new BigDecimal("0.15"); // 15%
    private static final BigDecimal EXTRA_HOUR_RATE = new BigDecimal("35000");
    
    @Override
    public BigDecimal calculate(Employee employee, int months) {
        BigDecimal baseSalary = employee.getCurrentSalary()
            .multiply(BigDecimal.valueOf(months));
        
        // Comisión sobre ventas (simulado)
        BigDecimal monthlySales = new BigDecimal("8000000");
        BigDecimal commission = monthlySales
            .multiply(COMMISSION_RATE)
            .multiply(BigDecimal.valueOf(months));
        
        // Calcular horas extras
        BigDecimal extraSalary = BigDecimal.ZERO;
        if (employee.getExtraHours() > 0) {
            extraSalary = EXTRA_HOUR_RATE
                .multiply(BigDecimal.valueOf(employee.getExtraHours()))
                .multiply(BigDecimal.valueOf(months));
        }
        
        return baseSalary.add(commission).add(extraSalary);
    }
}