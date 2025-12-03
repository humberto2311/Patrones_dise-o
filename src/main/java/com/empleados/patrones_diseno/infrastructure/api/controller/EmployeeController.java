package com.empleados.patrones_diseno.infrastructure.api.controller;

import com.empleados.patrones_diseno.application.EmployeeGet;
import com.empleados.patrones_diseno.application.EmployeeSave;
import com.empleados.patrones_diseno.application.EmployeeUpdate;
import com.empleados.patrones_diseno.domain.entities.Employee;
import com.empleados.patrones_diseno.infrastructure.dto.EmployeeDTO;
import com.empleados.patrones_diseno.infrastructure.mapper.EmployeeMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeGet employeeGet;
    private final EmployeeSave employeeSave;
    private final EmployeeUpdate employeeUpdate;
    private final EmployeeMapper employeeMapper;

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployee(@PathVariable Integer id) {
        Optional<Employee> employee = employeeGet.getEmployeeById(id);

        EmployeeDTO employeeDTO = employeeMapper.toDTO(employee.orElse(null));
        return new ResponseEntity<>(employeeDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> saveEmployee(@RequestBody EmployeeDTO employeeDTO) {

        Employee employee = employeeMapper.toEntity(employeeDTO);
        Employee savedEmployee = employeeSave.saveEmployee(employee);

        EmployeeDTO savedDTO = employeeMapper.toDTO(savedEmployee);
        return new ResponseEntity<>(savedDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable Integer id, @RequestBody EmployeeDTO employeeDTO) {
        // Verificar si el empleado existe
        Optional<Employee> existingEmployee = employeeGet.getEmployeeById(id);

        if (existingEmployee.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        employeeDTO.setEmployeeId(id);
        Employee employee = employeeMapper.toEntity(employeeDTO);

        // Mantener campos específicos del dominio
        employee.setEmployeeType(existingEmployee.get().getEmployeeType());
        employee.setExtraHours(existingEmployee.get().getExtraHours());
        employee.setHireDate(existingEmployee.get().getHireDate());
        employee.setCurrentRole(existingEmployee.get().getCurrentRole());

        // Guardar actualización
        Employee updatedEmployee = employeeSave.saveEmployee(employee);
        EmployeeDTO updatedDTO = employeeMapper.toDTO(updatedEmployee);

        return new ResponseEntity<>(updatedDTO, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Integer id) {
        // Aquí necesitarías implementar la lógica de eliminación
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}