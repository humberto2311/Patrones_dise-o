package com.empleados.patrones_diseno.domain.decorator.service;

import com.empleados.patrones_diseno.domain.decorator.SalaryCalculator;

import java.math.BigDecimal;

public abstract class BonusDecorator implements SalaryCalculator {
    
    protected SalaryCalculator decoratedCalculator;
    
    public BonusDecorator(SalaryCalculator calculator) {
        this.decoratedCalculator = calculator;
    }
    
    @Override
    public BigDecimal calculate() {
        return decoratedCalculator.calculate();
    }
}