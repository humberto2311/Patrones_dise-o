package com.empleados.patrones_diseno.domain.decorator.service;

import com.empleados.patrones_diseno.domain.decorator.SalaryCalculator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class SeniorityBonusDecorator extends BonusDecorator {
    
    private final LocalDateTime hireDate;
    
    public SeniorityBonusDecorator(SalaryCalculator calculator, LocalDateTime hireDate) {
        super(calculator);
        this.hireDate = hireDate;
    }
    
    @Override
    public BigDecimal calculate() {
        BigDecimal baseSalary = decoratedCalculator.calculate();
        
        // Calcular años de antigüedad
        long years = ChronoUnit.YEARS.between(hireDate, LocalDateTime.now());
        
        if (years >= 5) {
            BigDecimal bonusPercentage = new BigDecimal("0.10"); // 10% por 5+ años
            BigDecimal bonus = baseSalary.multiply(bonusPercentage);
            return baseSalary.add(bonus);
        }
        
        return baseSalary;
    }
}