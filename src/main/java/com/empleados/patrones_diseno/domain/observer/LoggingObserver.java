package com.empleados.patrones_diseno.domain.observer;

import com.empleados.patrones_diseno.domain.entities.SalaryHistory;
import com.empleados.patrones_diseno.domain.services.SalaryObserverService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
public class LoggingObserver implements SalaryObserverService {


    @Override
    public void update(Optional<SalaryHistory> salaryHistoryOptional) {
        if (salaryHistoryOptional.isPresent()) {
            SalaryHistory salaryHistory = salaryHistoryOptional.get();
            log.info("📊 Log de cambio salarial:");
            log.info("Empleado: {} {}",
                    salaryHistory.getEmployee().getFirstName(),
                    salaryHistory.getEmployee().getLastName());
            log.info("Nuevo salario: {}", salaryHistory.getSalaryAmount());
            log.info("Fecha: {}", salaryHistory.getStartDate());
        } else {
            log.warn("⚠️ No se puede registrar log: datos de historial salarial no disponibles");
        }
    }
}