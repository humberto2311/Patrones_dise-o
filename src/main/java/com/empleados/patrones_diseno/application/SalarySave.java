package com.empleados.patrones_diseno.application;


import com.empleados.patrones_diseno.domain.entities.SalaryHistory;
import com.empleados.patrones_diseno.domain.services.SalarySaveService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class SalarySave {

    private final SalarySaveService salaryHistorySave;

    public SalaryHistory saveSalary(SalaryHistory salaryHistory) {
        return salaryHistorySave.saveNewHistoryEntry(salaryHistory);
    }
}
