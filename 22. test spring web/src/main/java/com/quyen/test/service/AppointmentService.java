package com.quyen.test.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quyen.test.entity.Appointment;
import com.quyen.test.model.request.AppointmentRequest;
import com.quyen.test.model.request.SearchAppointmentRequest;
import com.quyen.test.model.response.AppointmentDetailResponse;
import com.quyen.test.model.response.AppointmentResponse;
import com.quyen.test.repository.AppointmentJpaRepository;
import com.quyen.test.repository.AppointmentRepository;
import com.quyen.test.statics.Status;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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
    private final AppointmentRepository appointmentRepository;


    public void save(AppointmentRequest request) {
        Appointment appointment = objectMapper.convertValue(request, Appointment.class);
        appointment.setStatus(Status.PENDING);
        appointment.setCreatedAt(LocalDateTime.now());
        appointmentJpaRepository.save(appointment);
    }

    public List<AppointmentDetailResponse> getAll() {
        List<Appointment> appointments = appointmentJpaRepository.findAll();
        return appointments.stream().map(
                appointment -> AppointmentDetailResponse.builder()
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


    public AppointmentResponse searchAppointment(SearchAppointmentRequest request) {
        List<AppointmentDetailResponse> data = appointmentRepository.searchAppointment(request);
        Long totalElement = 0L;
        if (!CollectionUtils.isEmpty(data)) {
            totalElement = data.get(0).getTotalRecord();
        }

        double totalPageTemp = (double) totalElement / request.getPageSize();
        if (totalPageTemp > totalElement / request.getPageSize()) {
            totalPageTemp++;
        }

        return AppointmentResponse.builder()
                .appointments(data)
                .totalElement(totalElement)
                .totalPage(Double.valueOf(totalPageTemp).intValue())
                .currentPage(request.getCurrentPage())
                .pageSize(request.getPageSize())
                .build();
    }
}
