package com.empleados.patrones_diseno.domain.strategy;

import com.empleados.patrones_diseno.domain.services.SalaryUpdateService;

public class SalaryStrategyFactory {

    public static SalaryUpdateService.SalaryStrategyService createStrategy(String employeeType) {
        switch (employeeType.toUpperCase()) {
            case "FIJO":
                return new FixedSalaryStrategy();
            case "POR_HORAS":
                return new HourlySalaryStrategy();
            case "COMISION":
                return new CommissionSalaryStrategy();
            default:
                throw new IllegalArgumentException("Tipo de empleado no soportado: " + employeeType);
        }
    }
}