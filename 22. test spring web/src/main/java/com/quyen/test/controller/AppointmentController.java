package com.quyen.test.controller;

import com.quyen.test.model.request.AppointmentRequest;
import com.quyen.test.model.response.AppointmentResponse;
import com.quyen.test.service.AppointmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/admin/appointments")
public class AppointmentController {
    private final AppointmentService appointmentService;

    @GetMapping
    public String getAllProduct(Model model) {
        List<AppointmentResponse> appointments = appointmentService.getAll();
        model.addAttribute("appointments", appointments);
        return "appointment/appointments";
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody AppointmentRequest request) {
        appointmentService.save(request);
        return ResponseEntity.ok(null);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> approveAppointment(@PathVariable Long id) throws MessagingException {
        appointmentService.approve(id);
        appointmentService.sendAppovedEmail(id);
        return ResponseEntity.ok(null);
    }

    @PutMapping("/reject/{id}")
    public ResponseEntity<?> rejectAppointment(@PathVariable Long id) throws MessagingException {
        appointmentService.reject(id);
        appointmentService.sendRejectEmail(id);
        return ResponseEntity.ok(null);
    }

}
