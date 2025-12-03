package com.empleados.patrones_diseno.infrastructure.api.controller;

import com.empleados.patrones_diseno.application.SalaryGet;
import com.empleados.patrones_diseno.application.SalarySave;
import com.empleados.patrones_diseno.application.SalaryUpdate;
import com.empleados.patrones_diseno.domain.entities.SalaryHistory;
import com.empleados.patrones_diseno.infrastructure.dto.SalaryHistoryDTO;
import com.empleados.patrones_diseno.infrastructure.mapper.SalaryMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/salaries")
public class SalaryController {

    private final SalaryGet salaryGet;
    private final SalarySave salarySave;
    private final SalaryUpdate salaryUpdate;
    private final SalaryMapper salaryMapper;

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<SalaryHistoryDTO>> getSalaryHistoryByEmployee(@PathVariable Integer employeeId) {
        List<SalaryHistory> historyList = salaryGet.getHistoryByEmployeeId(employeeId);
        List<SalaryHistoryDTO> dtoList = historyList.stream()
                .map(salaryMapper::toDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    @GetMapping("/entry/{entryId}")
    public ResponseEntity<SalaryHistoryDTO> getSalaryEntry(@PathVariable Integer entryId) {
        SalaryHistory history = salaryGet.getHistoryEntryById(entryId);
        SalaryHistoryDTO dto = salaryMapper.toDTO(history);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SalaryHistoryDTO> saveSalaryEntry(@RequestBody SalaryHistoryDTO salaryHistoryDTO) {
        SalaryHistory history = salaryMapper.toEntity(salaryHistoryDTO);
        SalaryHistory savedHistory = salarySave.saveSalary(history);
        SalaryHistoryDTO savedDTO = salaryMapper.toDTO(savedHistory);
        return new ResponseEntity<>(savedDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{entryId}")
    public ResponseEntity<SalaryHistoryDTO> updateSalaryEntry(@PathVariable Integer entryId,
                                                              @RequestBody SalaryHistoryDTO salaryHistoryDTO) {

        SalaryHistory existingEntry = salaryGet.getHistoryEntryById(entryId);
        if (existingEntry == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        salaryHistoryDTO.setEntryId(entryId);
        SalaryHistory history = salaryMapper.toEntity(salaryHistoryDTO);
        SalaryHistory updatedHistory = salarySave.saveSalary(history);
        SalaryHistoryDTO updatedDTO = salaryMapper.toDTO(updatedHistory);
        return new ResponseEntity<>(updatedDTO, HttpStatus.OK);
    }
}