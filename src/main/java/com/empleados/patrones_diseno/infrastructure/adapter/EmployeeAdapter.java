package com.empleados.patrones_diseno.infrastructure.adapter;



import com.empleados.patrones_diseno.domain.entities.Employee;
import com.empleados.patrones_diseno.domain.services.EmployeeGetService;
import com.empleados.patrones_diseno.domain.services.EmployeeSaveService;
import com.empleados.patrones_diseno.domain.services.EmployeeUpdateService;
import com.empleados.patrones_diseno.infrastructure.mapper.EmployeeMapper;
import com.empleados.patrones_diseno.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class EmployeeAdapter implements EmployeeGetService, EmployeeSaveService, EmployeeUpdateService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    @Override
    public Employee saveEmployee(Employee employee) {
        var employeeEntity = employeeMapper.toDTO(employee);
        var savedEntity = employeeRepository.save(employeeEntity);
        return employeeMapper.toEntity(savedEntity);
    }

    @Override
    public Optional<Employee> getEmployeeById(int id) {
        return employeeRepository.findById(id)
                .map(employeeMapper::toEntity);
    }

    @Override
    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll().stream()
                .map(employeeMapper::toEntity)
                .collect(Collectors.toList());
    }

   /* @Override
    public void deleteEmployee(int id) {
        employeeRepository.deleteById(id);
    }*/

    @Override
    public Employee updateEmployee(Employee employee) {
        return saveEmployee(employee);
    }
}
