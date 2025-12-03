package com.empleados.patrones_diseno.domain.observer;

import com.empleados.patrones_diseno.domain.entities.SalaryHistory;
import com.empleados.patrones_diseno.domain.services.SalaryObserverService;
import org.springframework.stereotype.Component;

@Component
public class EmailNotificationObserver implements SalaryObserverService {
    
    @Override
    public void update(SalaryHistory salaryHistory) {
        System.out.println("📧 Enviando email de notificación...");
        System.out.println("Empleado ID: " + salaryHistory.getEmployee().getEmployeeId());
        System.out.println("Nuevo salario: " + salaryHistory.getSalaryAmount());
        System.out.println("Razón: " + salaryHistory.getReason());
        System.out.println("---");
        // Aquí iría la lógica real para enviar el email
    }
}