package com.empleados.patrones_diseno.domain.decorator.service;

import com.empleados.patrones_diseno.domain.decorator.SalaryCalculator;

import java.math.BigDecimal;

public class PerformanceBonusDecorator extends BonusDecorator {
    
    private static final BigDecimal BONUS_PERCENTAGE = new BigDecimal("0.15"); // 15%
    
    public PerformanceBonusDecorator(SalaryCalculator calculator) {
        super(calculator);
    }
    
    @Override
    public BigDecimal calculate() {
        BigDecimal baseSalary = decoratedCalculator.calculate();
        BigDecimal bonus = baseSalary.multiply(BONUS_PERCENTAGE);
        return baseSalary.add(bonus);
    }
}