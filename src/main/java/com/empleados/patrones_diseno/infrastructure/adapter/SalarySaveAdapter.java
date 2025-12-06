package com.empleados.patrones_diseno.infrastructure.adapter;

import com.empleados.patrones_diseno.domain.entities.SalaryHistory;
import com.empleados.patrones_diseno.domain.services.SalarySaveService;
import com.empleados.patrones_diseno.repository.SalaryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class SalarySaveAdapter  implements SalarySaveService {
    private final SalaryRepository salaryRepository;

    public SalaryHistory saveNewHistoryEntry(SalaryHistory historyEntry) {
        return salaryRepository.save(historyEntry);
    }



}
