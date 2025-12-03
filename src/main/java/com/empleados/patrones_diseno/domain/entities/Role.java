package com.empleados.patrones_diseno.domain.entities;

import jakarta.persistence.*;
import lombok.Data;


import java.math.BigDecimal;
@Entity
@Table(name = "ROLES")
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROLE_ID")
    int roleid;
    @Column(name = "ROLE_NAME", unique = true, nullable = false)
    String rolename;
    @Column(name = "BASE_SALARY_RANGE", precision = 10, scale = 2)
    BigDecimal baseSalaryRange;
}
