package com.empleados.patrones_diseno.infrastructure.adapter;

import com.empleados.patrones_diseno.domain.entities.SalaryHistory;
import com.empleados.patrones_diseno.domain.services.SalaryUpdateService;
import com.empleados.patrones_diseno.repository.SalaryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@AllArgsConstructor
@Component
public class SalaryUpdateAdapter implements SalaryUpdateService {
    private final SalaryRepository salaryRepository;


    @Override
    public SalaryHistory updateHistoryEntry(SalaryHistory historyEntry) {
        return  salaryRepository.save(historyEntry);
    }

    @Override
    public Optional<SalaryHistory> getHistoryEntryById(int entryId) {
        return salaryRepository.findById(entryId);
    }
}
