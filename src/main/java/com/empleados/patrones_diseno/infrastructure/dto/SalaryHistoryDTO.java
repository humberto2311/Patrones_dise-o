package com.empleados.patrones_diseno.infrastructure.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class SalaryHistoryDTO {
    int entryId;
    int employeeId;
    LocalDateTime startDate;
    BigDecimal salaryAmount;
    String reason;
}
