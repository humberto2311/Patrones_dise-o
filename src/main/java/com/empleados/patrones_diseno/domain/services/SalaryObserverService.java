package com.empleados.patrones_diseno.domain.services;

import com.empleados.patrones_diseno.domain.entities.SalaryHistory;

public interface SalaryObserverService {
    void update(SalaryHistory salaryHistory);
}