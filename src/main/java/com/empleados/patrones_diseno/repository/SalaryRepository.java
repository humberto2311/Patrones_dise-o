package com.empleados.patrones_diseno.repository;

import com.empleados.patrones_diseno.infrastructure.dto.SalaryHistoryDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaryRepository extends JpaRepository<SalaryHistoryDTO, Integer> {
}
