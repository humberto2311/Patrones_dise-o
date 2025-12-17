package com.empleados.patrones_diseno.infrastructure.api.controller;

import com.empleados.patrones_diseno.application.RoleGet;
import com.empleados.patrones_diseno.application.RoleSave;
import com.empleados.patrones_diseno.application.RoleUpdate;
import com.empleados.patrones_diseno.domain.entities.Role;
import com.empleados.patrones_diseno.infrastructure.dto.RoleDTO;
import com.empleados.patrones_diseno.infrastructure.mapper.RoleMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class RoleControllerTest {

    private MockMvc mockMvc;

    @Mock
    private RoleSave roleSave;

    @Mock
    private RoleGet roleGet;

    @Mock
    private RoleUpdate roleUpdate;

    @Mock
    private RoleMap roleMap;

    @InjectMocks
    private RoleController roleController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(roleController).build();
    }

    // ==================================
    //       TEST GET EXISTENTE
    // ==================================
    @Test
    void getRole_ReturnsRole_WhenExists() throws Exception {

        Role role = new Role();
        role.setRoleid(1);
        role.setRolename("Manager");
        role.setBaseSalaryRange(new BigDecimal("4500.00"));

        RoleDTO dto = new RoleDTO(1, "Manager", new BigDecimal("4500.00"));

        when(roleGet.getRoleById(1)).thenReturn(Optional.of(role));
        when(roleMap.toDTO(role)).thenReturn(dto);

        mockMvc.perform(get("/roles?id=1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.roleid").value(1))
                .andExpect(jsonPath("$.rolename").value("Manager"))
                .andExpect(jsonPath("$.baseSalaryRange").value(4500.00));
    }

    // ==================================
    //      TEST GET NO EXISTENTE
    // ==================================
    @Test
    void getRole_ReturnsNullDTO_WhenNotFound() throws Exception {

        when(roleGet.getRoleById(99)).thenReturn(Optional.empty());
        when(roleMap.toDTO(null)).thenReturn(null);

        mockMvc.perform(get("/roles?id=99"))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    // ==================================
    //           TEST POST
    // ==================================
    @Test
    void saveRole_ReturnsCreated() throws Exception {

        Role role = new Role();
        role.setRoleid(1);
        role.setRolename("Supervisor");
        role.setBaseSalaryRange(new BigDecimal("5200.00"));

        when(roleSave.saveRole(role)).thenReturn(role);

        mockMvc.perform(
                        post("/roles")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                                {
                                    "roleid": 1,
                                    "rolename": "Supervisor",
                                    "baseSalaryRange": 5200.00
                                }
                                """)
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.roleid").value(1))
                .andExpect(jsonPath("$.rolename").value("Supervisor"))
                .andExpect(jsonPath("$.baseSalaryRange").value(5200.00));
    }
}
