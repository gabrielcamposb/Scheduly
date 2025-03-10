package com.Scheduly.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Scheduly.model.Appointment;
import com.Scheduly.model.Availability;
import com.Scheduly.service.SchedulingService;

@RestController
@RequestMapping("/appointments")
public class SchedulingController {

    @Autowired
    private SchedulingService schedulingService;

    @GetMapping("/availability")
    public ResponseEntity<List<Availability>> searchAvailableTimes(
            @RequestParam Long employeeId,
            @RequestParam String start,
            @RequestParam String end) {
        try {
            LocalDateTime startDate = LocalDateTime.parse(start);
            LocalDateTime endDate = LocalDateTime.parse(end);

            List<Availability> availableTimes = schedulingService.searchAvailableTimes(employeeId, startDate, endDate);
            return new ResponseEntity<>(availableTimes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/schedule")
    public ResponseEntity<String> scheduleConsultation(
            @RequestParam Long patientId,
            @RequestParam Long employeeId,
            @RequestParam String dateHour) {
        try {
            LocalDateTime dateTimeSchedule = LocalDateTime.parse(dateHour);

            Appointment appointment = schedulingService.scheduleConsultation(patientId, employeeId, dateTimeSchedule);

            return new ResponseEntity<>("Consulta agendada com sucesso! ID: " + appointment.getId(),
                    HttpStatus.CREATED);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao agendar consulta", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
