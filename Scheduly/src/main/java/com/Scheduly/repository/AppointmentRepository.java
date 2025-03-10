package com.Scheduly.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Scheduly.model.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByEmployeeId(Long employeeId);
    List<Appointment> findByEmployeeIdAndDateHour(Long employeeId, LocalDateTime dateHour);
    List<Appointment> findByEmployeeIdAndDateHourBetween(Long employeeId, LocalDateTime start, LocalDateTime end);
}
