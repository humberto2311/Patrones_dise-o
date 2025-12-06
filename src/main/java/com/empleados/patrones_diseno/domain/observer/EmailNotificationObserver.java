package com.empleados.patrones_diseno.domain.observer;

import com.empleados.patrones_diseno.domain.entities.SalaryHistory;
import com.empleados.patrones_diseno.domain.services.SalaryObserverService;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class EmailNotificationObserver implements SalaryObserverService {

    @Override
    public void update(Optional<SalaryHistory> salaryHistoryOptional) {
        if (salaryHistoryOptional.isPresent()) {
            SalaryHistory salaryHistory = salaryHistoryOptional.get();
            System.out.println("📧 Enviando email de notificación...");
            System.out.println("Empleado ID: " + salaryHistory.getEmployee().getEmployeeId());
            System.out.println("Nuevo salario: " + salaryHistory.getSalaryAmount());
            System.out.println("Razón: " + salaryHistory.getReason());
            System.out.println("---");

        } else {
            System.out.println("No se puede enviar email: datos de historial salarial no disponibles");
        }
    }
}