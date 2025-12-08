package com.empleados.patrones_diseno.infrastructure.api.controller;

import com.empleados.patrones_diseno.application.SalaryGet;
import com.empleados.patrones_diseno.application.SalarySave;
import com.empleados.patrones_diseno.application.SalaryUpdate;
import com.empleados.patrones_diseno.domain.entities.Employee;
import com.empleados.patrones_diseno.domain.entities.SalaryHistory;
import com.empleados.patrones_diseno.infrastructure.dto.SalaryHistoryDTO;
import com.empleados.patrones_diseno.infrastructure.mapper.SalaryMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SalaryController.class)
@AutoConfigureMockMvc(addFilters = false)
class SalaryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SalaryGet salaryGet;

    @MockBean
    private SalarySave salarySave;

    @MockBean
    private SalaryUpdate salaryUpdate;

    @MockBean
    private SalaryMapper salaryMapper;

    // ---------------------------
    // FIXTURES
    // ---------------------------
    SalaryHistory buildEntity() {
        SalaryHistory entity = new SalaryHistory();
        entity.setEntryId(1);

        Employee emp = new Employee();
        emp.setEmployeeId(10);
        entity.setEmployee(emp);

        entity.setStartDate(LocalDateTime.of(2024, 1, 1, 10, 0));
        entity.setSalaryAmount(new BigDecimal("3200.00"));
        entity.setReason("Initial Salary");
        return entity;
    }

    SalaryHistoryDTO buildDto() {
        SalaryHistoryDTO dto = new SalaryHistoryDTO();
        dto.setEntryId(1);
        dto.setEmployeeId(10);
        dto.setStartDate(LocalDateTime.of(2024, 1, 1, 10, 0));
        dto.setSalaryAmount(new BigDecimal("3200.00"));
        dto.setReason("Initial Salary");
        return dto;
    }

    // ---------------------------
    // GET /employee/{id}
    // ---------------------------
    @Test
    void getSalaryHistoryByEmployee_ReturnsList() throws Exception {

        SalaryHistory entity = buildEntity();
        SalaryHistoryDTO dto = buildDto();

        when(salaryGet.getHistoryByEmployeeId(10))
                .thenReturn(Optional.of(entity));

        when(salaryMapper.toDTO(entity)).thenReturn(dto);

        mockMvc.perform(get("/salaries/employee/10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].entryId").value(1))
                .andExpect(jsonPath("$[0].employeeId").value(10))
                .andExpect(jsonPath("$[0].salaryAmount").value(3200.00))
                .andExpect(jsonPath("$[0].reason").value("Initial Salary"));
    }

    // ---------------------------
    // GET /entry/{id}
    // ---------------------------
    @Test
    void getSalaryEntry_ReturnsDTO() throws Exception {

        SalaryHistory entity = buildEntity();
        SalaryHistoryDTO dto = buildDto();

        when(salaryGet.getHistoryEntryById(1)).thenReturn(Optional.of(entity));
        when(salaryMapper.toDTO(entity)).thenReturn(dto);

        mockMvc.perform(get("/salaries/entry/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.entryId").value(1))
                .andExpect(jsonPath("$.employeeId").value(10))
                .andExpect(jsonPath("$.reason").value("Initial Salary"));
    }

    @Test
    void getSalaryEntry_ReturnsNull_WhenNotFound() throws Exception {

        when(salaryGet.getHistoryEntryById(99)).thenReturn(Optional.empty());
        when(salaryMapper.toDTO(null)).thenReturn(null);

        mockMvc.perform(get("/salaries/entry/99"))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    // ---------------------------
    // POST /salaries
    // ---------------------------
    @Test
    void saveSalaryEntry_CreatesEntry() throws Exception {

        SalaryHistory entity = buildEntity();
        SalaryHistoryDTO dto = buildDto();

        when(salaryMapper.toEntity(any())).thenReturn(entity);
        when(salarySave.saveSalary(entity)).thenReturn(entity);
        when(salaryMapper.toDTO(entity)).thenReturn(dto);

        String requestJson = """
            {
                "employeeId": 10,
                "startDate": "2024-01-01T10:00:00",
                "salaryAmount": 3200.00,
                "reason": "Initial Salary"
            }
            """;

        mockMvc.perform(post("/salaries")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.employeeId").value(10))
                .andExpect(jsonPath("$.salaryAmount").value(3200.00));
    }

    // ---------------------------
    // PUT /salaries/{id}
    // ---------------------------
    @Test
    void updateSalaryEntry_NotFound() throws Exception {

        when(salaryGet.getHistoryEntryById(1)).thenReturn(Optional.empty());

        String requestJson = """
            {
                "employeeId": 10,
                "startDate": "2024-01-01T10:00:00",
                "salaryAmount": 4000.00,
                "reason": "Raise"
            }
            """;

        mockMvc.perform(put("/salaries/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk());
    }

    @Test
    void updateSalaryEntry_Success() throws Exception {

        SalaryHistory entity = buildEntity();
        SalaryHistoryDTO dto = buildDto();

        when(salaryGet.getHistoryEntryById(1)).thenReturn(Optional.of(entity));
        when(salaryMapper.toEntity(any())).thenReturn(entity);
        when(salarySave.saveSalary(entity)).thenReturn(entity);
        when(salaryMapper.toDTO(entity)).thenReturn(dto);

        String requestJson = """
            {
                "employeeId": 10,
                "startDate": "2024-01-01T10:00:00",
                "salaryAmount": 3500.00,
                "reason": "Annual raise"
            }
            """;

        mockMvc.perform(put("/salaries/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.entryId").value(1))
                .andExpect(jsonPath("$.employeeId").value(10));
    }
}
