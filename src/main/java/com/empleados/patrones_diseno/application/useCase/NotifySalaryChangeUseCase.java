package com.empleados.patrones_diseno.application.useCase;

import com.empleados.patrones_diseno.domain.services.CalculoSalarioStrategy;
import com.empleados.patrones_diseno.domain.services.SalaryGetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotifySalaryChangeUseCase {
    
    private final CalculoSalarioStrategy.NotificationPortService notificationPort;
    private final SalaryGetService salaryHistoryPort;
    
    public void execute(int salaryHistoryId) {
        var salaryHistory = salaryHistoryPort.getHistoryEntryById(salaryHistoryId);
        
        notificationPort.notifySalaryChange(salaryHistory);
    }
}