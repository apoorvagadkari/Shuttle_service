package com.shuttle_service.repository;

import com.shuttle_service.model.ShuttleRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShuttleRequestRepository extends JpaRepository<ShuttleRequest, Long> {
}
