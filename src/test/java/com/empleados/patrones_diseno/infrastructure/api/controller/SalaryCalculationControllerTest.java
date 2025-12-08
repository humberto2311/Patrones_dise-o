package com.empleados.patrones_diseno.infrastructure.api.controller;

import com.empleados.patrones_diseno.application.useCase.CalculateEmployeeSalaryUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc(addFilters = false)
class SalaryCalculationControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CalculateEmployeeSalaryUseCase calculateEmployeeSalaryUseCase;

    @InjectMocks
    private SalaryCalculationController salaryCalculationController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(salaryCalculationController).build();
    }

    // ======================================================
    // TEST: GET /salary-calculation/employee/{id}
    // ======================================================
    @Test
    void calculateSalary_Returns200_WhenSuccessful() throws Exception {

        when(calculateEmployeeSalaryUseCase.execute(1, 1, false))
                .thenReturn(new BigDecimal("3500.00"));

        mockMvc.perform(get("/salary-calculation/employee/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("3500.00"));
    }

    @Test
    void calculateSalary_UsesQueryParamsCorrectly() throws Exception {

        when(calculateEmployeeSalaryUseCase.execute(5, 6, true))
                .thenReturn(new BigDecimal("21000.00"));

        mockMvc.perform(
                        get("/salary-calculation/employee/5")
                                .param("months", "6")
                                .param("withBonus", "true")
                )
                .andExpect(status().isOk())
                .andExpect(content().string("21000.00"));
    }

    @Test
    void calculateSalary_Returns404_WhenEmployeeNotFound() throws Exception {

        when(calculateEmployeeSalaryUseCase.execute(99, 1, false))
                .thenThrow(new RuntimeException("Employee not found"));

        mockMvc.perform(get("/salary-calculation/employee/99"))
                .andExpect(status().isNotFound());
    }

    // ======================================================
    // TEST: POST /salary-calculation/{employeeId}/extra-hours
    // ======================================================
    @Test
    void registerExtraHours_Returns200_WhenSuccessful() throws Exception {

        doNothing().when(calculateEmployeeSalaryUseCase)
                .registerExtraHours(1, 10);

        mockMvc.perform(
                        post("/salary-calculation/1/extra-hours")
                                .param("hours", "10")
                )
                .andExpect(status().isOk());
    }

    @Test
    void registerExtraHours_Returns500_WhenUseCaseFails() throws Exception {

        doThrow(new RuntimeException("Error registrando horas"))
                .when(calculateEmployeeSalaryUseCase)
                .registerExtraHours(1, 5);

        mockMvc.perform(
                        post("/salary-calculation/1/extra-hours")
                                .param("hours", "5")
                )
                .andExpect(status().is5xxServerError());
    }
}
