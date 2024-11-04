package com.shuttle_service.repository;

import com.shuttle_service.model.ETACalculation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ETACalculationRepository extends JpaRepository<ETACalculation, Integer> {
}
