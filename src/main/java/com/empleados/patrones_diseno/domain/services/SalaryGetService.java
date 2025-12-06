package com.empleados.patrones_diseno.domain.services;

import com.empleados.patrones_diseno.domain.entities.SalaryHistory;

import java.util.List;
import java.util.Optional;

public interface SalaryGetService {

    Optional<SalaryHistory> getHistoryByEmployeeId(int employeeId);
    Optional<SalaryHistory> getHistoryEntryById(int entryId);
}
