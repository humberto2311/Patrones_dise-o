package com.empleados.patrones_diseno.application;

import com.empleados.patrones_diseno.domain.entities.SalaryHistory;
import com.empleados.patrones_diseno.domain.services.SalaryGetService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class SalaryGet {
    private final SalaryGetService salaryGetServicie;

    public List<SalaryHistory> getHistoryByEmployeeId(int employeeId) {
        return salaryGetServicie.getHistoryByEmployeeId(employeeId);
    }

    public SalaryHistory getHistoryEntryById(int entryId){
        return salaryGetServicie.getHistoryEntryById(entryId);
    }
}
