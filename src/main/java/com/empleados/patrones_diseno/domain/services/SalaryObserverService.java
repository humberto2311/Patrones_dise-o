package com.empleados.patrones_diseno.domain.services;

import com.empleados.patrones_diseno.domain.entities.SalaryHistory;

import java.util.Optional;

public interface SalaryObserverService {
    void update(Optional<SalaryHistory> salaryHistory);
}