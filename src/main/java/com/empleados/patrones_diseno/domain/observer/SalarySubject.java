package com.empleados.patrones_diseno.domain.observer;

import com.empleados.patrones_diseno.domain.entities.SalaryHistory;
import com.empleados.patrones_diseno.domain.services.SalaryObserverService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SalarySubject {

    private final List<SalaryObserverService> observers = new ArrayList<>();

    public void addObserver(SalaryObserverService observer) {
        observers.add(observer);
    }

    public void removeObserver(SalaryObserverService observer) {
        observers.remove(observer);
    }

    public void notifyObservers(SalaryHistory salaryHistory) {
        for (SalaryObserverService observer : observers) {
            observer.update(Optional.of(salaryHistory));
        }
    }
}