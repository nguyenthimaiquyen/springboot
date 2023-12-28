package com.quyen.phanconglaixe.controller;

import com.quyen.phanconglaixe.exception.BusNotFoundException;
import com.quyen.phanconglaixe.exception.DriverNotFoundException;
import com.quyen.phanconglaixe.model.request.BusRequest;
import com.quyen.phanconglaixe.model.request.DriverRequest;
import com.quyen.phanconglaixe.model.response.BusResponse;
import com.quyen.phanconglaixe.model.response.DriverResponse;
import com.quyen.phanconglaixe.service.BusService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/api/v1/buses")
public class BusController {
    private final BusService busService;

    @GetMapping
    public String getBusPage(Model model) {
        List<BusResponse> buses = busService.getAll();
        model.addAttribute("buses", buses);
        return "bus/buses";
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBusDetails(@PathVariable Long id) throws BusNotFoundException {
        BusResponse bus = busService.getBusDetails(id);
        return ResponseEntity.ok(bus);
    }


    @PostMapping
    public ResponseEntity<?> createBus(@RequestBody BusRequest request) {
        busService.saveBus(request);
        return ResponseEntity.ok(null);
    }

    @PutMapping
    public ResponseEntity<?> updateBus(@RequestBody BusRequest request) {
        busService.saveBus(request);
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBus(@PathVariable Long id) {
        busService.deleteBus(id);
        return ResponseEntity.ok(null);
    }

}
