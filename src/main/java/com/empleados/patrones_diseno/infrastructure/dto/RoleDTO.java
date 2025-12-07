package com.empleados.patrones_diseno.infrastructure.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class RoleDTO {
    int roleid;
    String rolename;
    BigDecimal baseSalaryRange;
}
