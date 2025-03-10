package com.Scheduly.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Scheduly.model.Appointment;
import com.Scheduly.model.Availability;
import com.Scheduly.model.User;
import com.Scheduly.repository.AppointmentRepository;
import com.Scheduly.repository.AvailabilityRepository;
import com.Scheduly.repository.UserRepository;

@Service
public class SchedulingService {
    
    @Autowired
    private AvailabilityRepository availabilityRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    public List<Availability> searchAvailableTimes(Long employeeId, LocalDateTime start, LocalDateTime end) {
        return availabilityRepository.findByEmployeeIdAndStartBetween(employeeId, start, end);
    }    

    public Appointment scheduleConsultation(Long patientId, Long employeeId, LocalDateTime dateHour) {
        List<Appointment> existingAppointments = appointmentRepository.findByEmployeeIdAndDateHour(employeeId, dateHour);
        if (!existingAppointments.isEmpty()) {
            throw new IllegalStateException("Horário não disponível");
        }
    
        List<Availability> available = availabilityRepository.findByEmployeeIdAndStart(employeeId, dateHour);
        if (available.isEmpty() || !available.get(0).isAvailable()) {
            throw new IllegalStateException("Horário não disponível");
        }
    
        User patient = userRepository.findById(patientId).orElseThrow(() -> new IllegalStateException("Paciente não encontrado"));
        User employee = userRepository.findById(employeeId).orElseThrow(() -> new IllegalStateException("Funcionário não encontrado"));
    
        Appointment consultation = new Appointment();
        consultation.setPatient(patient);
        consultation.setEmployee(employee);
        consultation.setDateHour(dateHour);
        consultation.setStatus("Agendada");
    
        return appointmentRepository.save(consultation);
    }
}
