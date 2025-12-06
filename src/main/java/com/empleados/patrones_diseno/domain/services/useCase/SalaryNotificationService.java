package com.empleados.patrones_diseno.domain.services.useCase;

import com.empleados.patrones_diseno.domain.entities.SalaryHistory;

import com.empleados.patrones_diseno.domain.observer.EmailNotificationObserver;
import com.empleados.patrones_diseno.domain.observer.LoggingObserver;
import com.empleados.patrones_diseno.domain.services.CalculoSalarioStrategy;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SalaryNotificationService implements CalculoSalarioStrategy.NotificationPortService {

    private final EmailNotificationObserver emailObserver;
    private final LoggingObserver loggingObserver;
    private final List<Object> observers = new ArrayList<>();

    @PostConstruct
    public void init() {
        // Suscribir observadores después de que las dependencias estén inyectadas
        subscribe(emailObserver);
        subscribe(loggingObserver);
    }

    @Override
    public void notifySalaryChange(Optional<SalaryHistory> salaryHistory) {
        for (Object observer : observers) {
            if (observer instanceof EmailNotificationObserver) {
                ((EmailNotificationObserver) observer).update(salaryHistory);
            } else if (observer instanceof LoggingObserver) {
                ((LoggingObserver) observer).update(salaryHistory);
            }
        }
    }

    @Override
    public void subscribe(Object observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    @Override
    public void unsubscribe(Object observer) {
        observers.remove(observer);
    }

    public List<Object> getObservers() {
        return new ArrayList<>(observers);
    }
}