package com.empleados.patrones_diseno.domain.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "EMPLOYEES")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMPLOYEE_ID")
    private int employeeId;

    @Column(name = "FIRST_NAME", nullable = false, length = 100)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false, length = 100)
    private String lastName;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "PHONE_NUMBER", length = 20)
    private String phone;

    @Column(name = "HIRE_DATE", nullable = false)
    private LocalDateTime hireDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CURRENT_ROLE_ID", nullable = false)
    private Role currentRole;

    @Column(name = "CURRENT_SALARY", precision = 10, scale = 2)
    private BigDecimal currentSalary;

    @Column(name = "EMPLOYEE_TYPE", nullable = false)
    private String employeeType;

    @Column(name = "EXTRA_HOURS")
    private int extraHours;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SalaryHistory> salaryHistories;


}