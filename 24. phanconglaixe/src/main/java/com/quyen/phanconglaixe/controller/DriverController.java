package com.quyen.phanconglaixe.controller;

import com.quyen.phanconglaixe.exception.DriverNotFoundException;
import com.quyen.phanconglaixe.model.request.DriverRequest;
import com.quyen.phanconglaixe.model.response.DriverResponse;
import com.quyen.phanconglaixe.service.DriverService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/api/v1/drivers")
public class DriverController {
    private final DriverService driverService;

    @GetMapping
    public String getDriverPage(Model model) {
        List<DriverResponse> drivers = driverService.getAll();
        model.addAttribute("drivers", drivers);
        return "driver/drivers";
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDriverDetails(@PathVariable Long id) throws DriverNotFoundException {
        DriverResponse driver = driverService.getDriverDetails(id);
        return ResponseEntity.ok(driver);
    }

    @GetMapping("/level")
    public ResponseEntity<?> getDriverLevel() {
        return ResponseEntity.ok(driverService.getDriverLevel());
    }

    @PostMapping
    public ResponseEntity<?> createDriver(@RequestBody DriverRequest request) {
        driverService.saveDriver(request);
        return ResponseEntity.ok(null);
    }

    @PutMapping
    public ResponseEntity<?> updateDriver(@RequestBody DriverRequest request) {
        driverService.saveDriver(request);
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDriver(@PathVariable Long id) {
        driverService.deleteDriver(id);
        return ResponseEntity.ok(null);
    }

}
