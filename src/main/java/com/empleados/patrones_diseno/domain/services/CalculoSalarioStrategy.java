package com.empleados.patrones_diseno.domain.services;

import com.empleados.patrones_diseno.domain.entities.Employee;
import com.empleados.patrones_diseno.domain.entities.SalaryHistory;

import java.math.BigDecimal;
import java.util.Optional;

public interface CalculoSalarioStrategy {
    double calcularSalario(Employee empleado, int meses);

    interface NotificationPortService {
        void notifySalaryChange(Optional<SalaryHistory> salaryHistory);
        void subscribe(Object observer);
        void unsubscribe(Object observer);
    }

    interface SalaryCalculationPortService {
        BigDecimal calculateSalary(Employee employee, int months);
        void registerExtraHours(int employeeId, int hours);
        BigDecimal calculateWithBonus(Employee employee, int months);
    }
}