package com.empleados.patrones_diseno.infrastructure.adapter;

import com.empleados.patrones_diseno.domain.entities.SalaryHistory;

import com.empleados.patrones_diseno.domain.services.SalaryGetService;
import com.empleados.patrones_diseno.domain.services.SalarySaveService;
import com.empleados.patrones_diseno.domain.services.SalaryUpdateService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class SalaryAdapter{
    
    private final SalaryGetService salaryGetService;
    private final SalarySaveService salarySaveService;
    private final SalaryUpdateService salaryUpdateService;
    

    public List<SalaryHistory> getHistoryByEmployeeId(int employeeId) {
        return salaryGetService.getHistoryByEmployeeId(employeeId);
    }
    

    public SalaryHistory getHistoryEntryById(int entryId) {
        return salaryGetService.getHistoryEntryById(entryId);
    }
    

    public SalaryHistory saveNewHistoryEntry(SalaryHistory historyEntry) {
        return salarySaveService.saveNewHistoryEntry(historyEntry);
    }
    

    public SalaryHistory updateHistoryEntry(SalaryHistory historyEntry) {
        return salaryUpdateService.updateHistoryEntry(historyEntry);
    }
}