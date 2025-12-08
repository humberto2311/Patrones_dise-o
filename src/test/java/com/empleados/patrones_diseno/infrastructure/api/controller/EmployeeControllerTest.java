package com.empleados.patrones_diseno.infrastructure.api.controller;


import com.empleados.patrones_diseno.application.EmployeeGet;
import com.empleados.patrones_diseno.application.EmployeeSave;
import com.empleados.patrones_diseno.application.EmployeeUpdate;
import com.empleados.patrones_diseno.domain.entities.Employee;
import com.empleados.patrones_diseno.infrastructure.dto.EmployeeDTO;
import com.empleados.patrones_diseno.infrastructure.mapper.EmployeeMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmployeeController.class)
@AutoConfigureMockMvc(addFilters = false)
class EmployeeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeGet employeeGet;

    @MockBean
    private EmployeeSave employeeSave;

    @MockBean
    private EmployeeUpdate employeeUpdate;

    @MockBean
    private EmployeeMapper employeeMapper;

    // -------------------------
    //  GET EMPLOYEE BY ID
    // -------------------------
    @Test
    void getEmployee_ReturnsEmployeeDTO_WhenEmployeeExists() throws Exception {
        Employee employee = new Employee();
        employee.setEmployeeId(1);
        employee.setFirstName("Carlos");
        employee.setLastName("Lopez");

        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setEmployeeId(1);
        employeeDTO.setFirstName("Carlos");
        employeeDTO.setLastName("Lopez");

        Mockito.when(employeeGet.getEmployeeById(1)).thenReturn(Optional.of(employee));
        Mockito.when(employeeMapper.toDTO(employee)).thenReturn(employeeDTO);

        mockMvc.perform(get("/employees/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.employeeId").value(1))
                .andExpect(jsonPath("$.firstName").value("Carlos"));
    }

    // -------------------------
    //  POST EMPLOYEE
    // -------------------------
    @Test
    void saveEmployee_ReturnsCreatedEmployeeDTO() throws Exception {
        String jsonRequest = """
                {
                    "firstName": "Ana",
                    "lastName": "Torres",
                    "salary": 1500
                }
                """;

        Employee employee = new Employee();
        employee.setFirstName("Ana");
        employee.setLastName("Torres");

        Employee savedEmployee = new Employee();
        savedEmployee.setEmployeeId(10);
        savedEmployee.setFirstName("Ana");
        savedEmployee.setLastName("Torres");

        EmployeeDTO savedDTO = new EmployeeDTO();
        savedDTO.setEmployeeId(10);
        savedDTO.setFirstName("Ana");
        savedDTO.setLastName("Torres");

        Mockito.when(employeeMapper.toEntity(any(EmployeeDTO.class))).thenReturn(employee);
        Mockito.when(employeeSave.saveEmployee(employee)).thenReturn(savedEmployee);
        Mockito.when(employeeMapper.toDTO(savedEmployee)).thenReturn(savedDTO);

        mockMvc.perform(post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.employeeId").value(10))
                .andExpect(jsonPath("$.firstName").value("Ana"));
    }

    // -------------------------
    //  PUT EMPLOYEE
    // -------------------------
    @Test
    void updateEmployee_ReturnsUpdatedDTO_WhenEmployeeExists() throws Exception {

        String jsonRequest = """
                {
                    "firstName": "Luis",
                    "lastName": "Ramirez"
                }
                """;

        Employee existingEmployee = new Employee();
        existingEmployee.setEmployeeId(5);
        existingEmployee.setFirstName("Old");
        existingEmployee.setEmployeeType("FULL_TIME");
        existingEmployee.setHireDate(LocalDateTime.now());

        Employee updatedEmployee = new Employee();
        updatedEmployee.setEmployeeId(5);
        updatedEmployee.setFirstName("Luis");
        updatedEmployee.setLastName("Ramirez");

        EmployeeDTO updatedDTO = new EmployeeDTO();
        updatedDTO.setEmployeeId(5);
        updatedDTO.setFirstName("Luis");
        updatedDTO.setLastName("Ramirez");

        Mockito.when(employeeGet.getEmployeeById(5)).thenReturn(Optional.of(existingEmployee));
        Mockito.when(employeeMapper.toEntity(any(EmployeeDTO.class))).thenReturn(updatedEmployee);
        Mockito.when(employeeMapper.toDTO(updatedEmployee)).thenReturn(updatedDTO);
        Mockito.when(employeeSave.saveEmployee(any(Employee.class))).thenReturn(updatedEmployee);

        mockMvc.perform(put("/employees/5")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Luis"))
                .andExpect(jsonPath("$.lastName").value("Ramirez"));
    }

    // -------------------------
    //  PUT NOT FOUND
    // -------------------------
    @Test
    void updateEmployee_ReturnsNotFound_WhenEmployeeDoesNotExist() throws Exception {

        Mockito.when(employeeGet.getEmployeeById(999)).thenReturn(Optional.empty());

        mockMvc.perform(put("/employees/999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "firstName": "Luis"
                                }
                                """))
                .andExpect(status().isNotFound());
    }

    // -------------------------
    //  DELETE EMPLOYEE
    // -------------------------
    @Test
    void deleteEmployee_ReturnsNoContent() throws Exception {
        mockMvc.perform(delete("/employees/1"))
                .andExpect(status().isNoContent());
    }
}