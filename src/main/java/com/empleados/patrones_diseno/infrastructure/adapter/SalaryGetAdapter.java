package com.empleados.patrones_diseno.infrastructure.adapter;

import com.empleados.patrones_diseno.domain.entities.SalaryHistory;

import com.empleados.patrones_diseno.domain.services.SalaryGetService;
import com.empleados.patrones_diseno.domain.services.SalarySaveService;
import com.empleados.patrones_diseno.domain.services.SalaryUpdateService;
import com.empleados.patrones_diseno.repository.SalaryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class SalaryGetAdapter implements  SalaryGetService {

    private final SalaryRepository  salaryRepository;
    

    public Optional<SalaryHistory> getHistoryByEmployeeId(int employeeId) {
        return salaryRepository.findById(employeeId);
    }
    

    public Optional<SalaryHistory>getHistoryEntryById(int entryId) {
        return salaryRepository.findById(entryId);
    }
    

}