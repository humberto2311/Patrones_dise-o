package com.empleados.patrones_diseno.infrastructure.api.controller;

import com.empleados.patrones_diseno.application.useCase.CalculateEmployeeSalaryUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@AllArgsConstructor
@RequestMapping("/salary-calculation")
public class SalaryCalculationController {
    
    private final CalculateEmployeeSalaryUseCase calculateEmployeeSalaryUseCase;
    
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<BigDecimal> calculateSalary(
            @PathVariable Integer employeeId,
            @RequestParam(defaultValue = "1") int months,
            @RequestParam(defaultValue = "false") boolean withBonus) {
        
        try {
            BigDecimal salary = calculateEmployeeSalaryUseCase.execute(employeeId, months, withBonus);
            return new ResponseEntity<>(salary, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PostMapping("/{employeeId}/extra-hours")
    public ResponseEntity<Void> registerExtraHours(
            @PathVariable Integer employeeId,
            @RequestParam int hours) {

        try {
            calculateEmployeeSalaryUseCase.registerExtraHours(employeeId, hours);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}