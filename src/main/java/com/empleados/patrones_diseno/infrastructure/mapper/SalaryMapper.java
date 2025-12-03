package com.empleados.patrones_diseno.infrastructure.mapper;


import com.empleados.patrones_diseno.domain.entities.Employee;
import com.empleados.patrones_diseno.domain.entities.SalaryHistory;
import com.empleados.patrones_diseno.infrastructure.dto.SalaryHistoryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SalaryMapper {

    // Leer el id desde la relación employee
    @Mapping(source = "employee.employeeId", target = "employeeId")
    SalaryHistoryDTO toDTO(SalaryHistory salaryHistory);

    // Crear la relación employee a partir del employeeId del DTO
    @Mapping(source = "employeeId", target = "employee")
    SalaryHistory toEntity(SalaryHistoryDTO salaryHistoryDTO);

    // Método auxiliar que MapStruct usará para convertir Integer -> Employee
    default Employee map(Integer employeeId) {
        if (employeeId == null) {
            return null;
        }
        Employee e = new Employee();
        e.setEmployeeId(employeeId);
        return e;
    }
}
