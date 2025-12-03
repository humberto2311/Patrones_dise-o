package com.empleados.patrones_diseno.infrastructure.adapter;

import com.empleados.patrones_diseno.domain.decorator.*;
import com.empleados.patrones_diseno.domain.decorator.service.*;
import com.empleados.patrones_diseno.domain.entities.Employee;
import com.empleados.patrones_diseno.domain.services.CalculoSalarioStrategy;
import com.empleados.patrones_diseno.domain.services.SalaryUpdateService;
import com.empleados.patrones_diseno.domain.strategy.SalaryStrategyFactory;
import com.empleados.patrones_diseno.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class SalaryCalculationAdapter implements CalculoSalarioStrategy.SalaryCalculationPortService {
    
    private final EmployeeRepository employeeRepository;
    
    @Override
    public BigDecimal calculateSalary(Employee employee, int months) {
        // Usar la estrategia basada en el tipo de empleado
        SalaryUpdateService.SalaryStrategyService strategy = SalaryStrategyFactory.createStrategy(employee.getEmployeeType());
        return strategy.calculate(employee, months);
    }
    
    @Override
    public void registerExtraHours(int employeeId, int hours) {
        employeeRepository.findById(employeeId).ifPresent(employeeDto -> {
            // En un caso real, actualizarías las horas extras
            // Por ahora solo lo dejamos como marcador de posición
            System.out.println("Registradas " + hours + " horas extras para empleado " + employeeId);
        });
    }
    
    @Override
    public BigDecimal calculateWithBonus(Employee employee, int months) {
        // Calcular salario base
        SalaryUpdateService.SalaryStrategyService strategy = SalaryStrategyFactory.createStrategy(employee.getEmployeeType());
        BigDecimal baseSalary = strategy.calculate(employee, months);
        
        // Aplicar decoradores para bonificaciones
        SalaryCalculator calculator = new BaseSalaryCalculator(baseSalary);
        
        // Aplicar bonificación por antigüedad si aplica
        if (employee.getHireDate() != null) {
            calculator = new SeniorityBonusDecorator(calculator, employee.getHireDate());
        }
        
        // Aplicar bonificación por desempeño
        calculator = new PerformanceBonusDecorator(calculator);
        
        return calculator.calculate();
    }
}