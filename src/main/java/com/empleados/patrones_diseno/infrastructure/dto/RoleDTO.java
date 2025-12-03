package com.empleados.patrones_diseno.infrastructure.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RoleDTO {
    int roleid;
    String rolename;
    BigDecimal baseSalaryRange;
}
