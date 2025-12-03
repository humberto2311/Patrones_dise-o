package com.empleados.patrones_diseno.domain.services;

import com.empleados.patrones_diseno.domain.entities.SalaryHistory;

import java.util.List;

public interface SalaryGetService {

    /** * Busca todo el historial salarial asociado a un empleado.
     * @param employeeId El ID del empleado.
     * @return Lista de entradas del historial.
     */

    List<SalaryHistory> getHistoryByEmployeeId(int employeeId);
    SalaryHistory getHistoryEntryById(int entryId);
}
