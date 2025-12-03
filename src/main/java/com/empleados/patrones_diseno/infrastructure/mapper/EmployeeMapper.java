package com.empleados.patrones_diseno.infrastructure.mapper;
// java


import com.empleados.patrones_diseno.domain.entities.Employee;
import com.empleados.patrones_diseno.infrastructure.dto.EmployeeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {RoleMap.class, SalaryMapper.class})
public interface EmployeeMapper {

    @Mapping(source = "currentRole", target = "currentRole")
    @Mapping(source = "salaryHistories", target = "salaryHistories")
    @Mapping(source = "employeeId", target = "employeeId")
    EmployeeDTO toDTO(Employee employee);

    @Mapping(source = "currentRole", target = "currentRole")
    @Mapping(source = "salaryHistories", target = "salaryHistories")

    @Mapping(source = "employeeId", target = "employeeId")
    @Mapping(target = "employeeType", defaultValue = "REGULAR")
    @Mapping(target = "extraHours", defaultValue = "0")
    @Mapping(target = "hireDate", expression = "java(java.time.LocalDateTime.now())")
    Employee toEntity(EmployeeDTO employeeDTO);
}
