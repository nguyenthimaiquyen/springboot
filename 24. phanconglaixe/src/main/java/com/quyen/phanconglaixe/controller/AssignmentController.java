package com.quyen.phanconglaixe.controller;

import com.quyen.phanconglaixe.exception.AssignmentNotFoundException;
import com.quyen.phanconglaixe.exception.BusNotFoundException;
import com.quyen.phanconglaixe.exception.DriverNotFoundException;
import com.quyen.phanconglaixe.model.request.AssignmentRequest;
import com.quyen.phanconglaixe.model.request.BusRequest;
import com.quyen.phanconglaixe.model.response.AssignmentResponse;
import com.quyen.phanconglaixe.model.response.BusResponse;
import com.quyen.phanconglaixe.service.AssignmentService;
import com.quyen.phanconglaixe.service.BusService;
import com.quyen.phanconglaixe.service.DriverService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/api/v1/assignments")
public class AssignmentController {
    private final AssignmentService assignmentService;
    private final BusService busService;
    private final DriverService driverService;

    @GetMapping
    public String getAssignmentPage(Model model) {
        List<AssignmentResponse> assignments = assignmentService.getAll();
        model.addAttribute("assignments", assignments);
        return "assignment/assignments";
    }

    @GetMapping("/buses")
    public ResponseEntity<?> getBuses() {
        return ResponseEntity.ok(busService.getAll());
    }

    @GetMapping("/drivers")
    public ResponseEntity<?> getDrivers() {
        return ResponseEntity.ok(driverService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAssignmentDetails(@PathVariable Long id) throws AssignmentNotFoundException {
        AssignmentResponse assignment = assignmentService.getAssignmentDetails(id);
        return ResponseEntity.ok(assignment);
    }

    @PostMapping
    public ResponseEntity<?> createAssignment(@RequestBody AssignmentRequest request)
            throws DriverNotFoundException, BusNotFoundException {
        assignmentService.saveAssignment(request);
        return ResponseEntity.ok(null);
    }

    @PutMapping
    public ResponseEntity<?> updateAssignment(@RequestBody AssignmentRequest request)
            throws DriverNotFoundException, BusNotFoundException {
        assignmentService.saveAssignment(request);
        return ResponseEntity.ok(null);
    }


}
