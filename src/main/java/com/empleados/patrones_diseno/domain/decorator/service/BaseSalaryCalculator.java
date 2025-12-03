package com.empleados.patrones_diseno.domain.decorator.service;

import com.empleados.patrones_diseno.domain.decorator.SalaryCalculator;

import java.math.BigDecimal;

public class BaseSalaryCalculator implements SalaryCalculator {
    
    private final BigDecimal baseSalary;
    
    public BaseSalaryCalculator(BigDecimal baseSalary) {
        this.baseSalary = baseSalary;
    }
    
    @Override
    public BigDecimal calculate() {
        return baseSalary;
    }
}