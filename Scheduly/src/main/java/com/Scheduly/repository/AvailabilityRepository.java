package com.Scheduly.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Scheduly.model.Availability;

public interface AvailabilityRepository extends JpaRepository<Availability, Long> {
    List<Availability> findByEmployeeIdAndStart(Long employeeId, LocalDateTime start);
    List<Availability> findByEmployeeIdAndStartBetween(Long employeeId, LocalDateTime start, LocalDateTime end);
}