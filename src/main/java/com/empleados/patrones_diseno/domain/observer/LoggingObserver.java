package com.empleados.patrones_diseno.domain.observer;

import com.empleados.patrones_diseno.domain.entities.SalaryHistory;
import com.empleados.patrones_diseno.domain.services.SalaryObserverService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LoggingObserver implements SalaryObserverService {
    
    private static final Logger logger = LoggerFactory.getLogger(LoggingObserver.class);
    
    @Override
    public void update(SalaryHistory salaryHistory) {
        logger.info("📊 Log de cambio salarial:");
        logger.info("Empleado: {} {}", 
            salaryHistory.getEmployee().getFirstName(),
            salaryHistory.getEmployee()
        .getLastName());
        logger.info("Nuevo salario: {}", salaryHistory.getSalaryAmount());
        logger.info("Fecha: {}", salaryHistory.getStartDate());
    }
}