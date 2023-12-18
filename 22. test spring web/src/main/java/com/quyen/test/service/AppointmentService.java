package com.quyen.test.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quyen.test.entity.Appointment;
import com.quyen.test.model.request.AppointmentRequest;
import com.quyen.test.model.response.AppointmentResponse;
import com.quyen.test.repository.AppointmentJpaRepository;
import com.quyen.test.statics.Status;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AppointmentService {
    private final ObjectMapper objectMapper;
    private final AppointmentJpaRepository appointmentJpaRepository;
    private final EmailService emailService;


    public void save(AppointmentRequest request) {
        Appointment appointment = objectMapper.convertValue(request, Appointment.class);
        appointment.setStatus(Status.PENDING);
        appointment.setCreatedAt(LocalDateTime.now());
        appointmentJpaRepository.save(appointment);
    }

    public List<AppointmentResponse> getAll() {
        List<Appointment> appointments = appointmentJpaRepository.findAll();
        return appointments.stream().map(
                appointment -> AppointmentResponse.builder()
                        .id(appointment.getId())
                        .name(appointment.getName())
                        .phone(appointment.getPhone())
                        .email(appointment.getEmail())
                        .content(appointment.getContent())
                        .status(appointment.getStatus())
                        .createdAt(appointment.getCreatedAt())
                        .handledAt(appointment.getHandledAt())
                        .build()
        ).collect(Collectors.toList());

    }

    public void approve(Long id) {
        Appointment appointment = appointmentJpaRepository.findById(id).get();
        if (!appointment.getStatus().getCode().equals("PENDING")) {
            return;
        }
        appointment.setStatus(Status.APPROVED);
        appointment.setHandledAt(LocalDateTime.now());
        appointmentJpaRepository.save(appointment);
    }


    public void reject(Long id) {
        Appointment appointment = appointmentJpaRepository.findById(id).get();
        if (!appointment.getStatus().getCode().equals("PENDING")) {
            return;
        }
        appointment.setStatus(Status.REJECTED);
        appointment.setHandledAt(LocalDateTime.now());
        appointmentJpaRepository.save(appointment);
    }

    public void sendAppovedEmail(Long id) throws MessagingException {
        Appointment appointment = appointmentJpaRepository.findById(id).get();
        String email = appointment.getEmail();
        LocalDateTime handledAt = appointment.getHandledAt();
        String name = appointment.getName();
        emailService.sendApprovedMail(name, email, handledAt);
    }

    public void sendRejectEmail(Long id) throws MessagingException {
        Appointment appointment = appointmentJpaRepository.findById(id).get();
        String email = appointment.getEmail();
        String name = appointment.getName();
        emailService.sendRejectedMail(name, email);
    }


}
