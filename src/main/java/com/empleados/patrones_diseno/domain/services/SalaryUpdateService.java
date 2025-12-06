package com.empleados.patrones_diseno.domain.services;

import com.empleados.patrones_diseno.domain.entities.Employee;
import com.empleados.patrones_diseno.domain.entities.SalaryHistory;

import java.math.BigDecimal;
import java.util.Optional;

public interface SalaryUpdateService {
    SalaryHistory updateHistoryEntry(SalaryHistory historyEntry);
   Optional <SalaryHistory> getHistoryEntryById(int entryId);

    interface SalaryStrategyService {
        BigDecimal calculate(Employee employee, int months);
    }
}
