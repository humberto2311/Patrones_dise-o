package com.empleados.patrones_diseno.infrastructure.dto;


import com.empleados.patrones_diseno.domain.entities.Role;
import com.empleados.patrones_diseno.domain.entities.SalaryHistory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class EmployeeDTO {

    private int employeeId;

    String firstName;

    String lastName;

    String address;

    String phone;

    LocalDateTime hireDate;

    Role currentRole;

    BigDecimal currentSalary;

    String employeeType;

    int extraHours;

    List<SalaryHistory> salaryHistories;


    }
