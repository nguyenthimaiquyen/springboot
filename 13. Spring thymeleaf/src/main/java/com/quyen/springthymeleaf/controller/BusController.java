package com.quyen.springthymeleaf.controller;

import com.quyen.springthymeleaf.entity.Bus;
import com.quyen.springthymeleaf.service.BusService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
public class BusController {
    private final BusService busService;

    @GetMapping("/buses")
    public String home(Model model) {
        List<Bus> busList = busService.getAllBus();
        model.addAttribute("busList", busList);
        return "bus/buses";
    }

    @GetMapping("/buses/create")
    public String create(Model model) {
        Bus newBus = new Bus();
        model.addAttribute("bus", newBus);
        return "/bus/bus-creation";
    }

    @PostMapping("/buses/create")
    public String create(@ModelAttribute("bus") Bus bus, Model model) {
        busService.create(bus);
        List<Bus> busList = busService.getAllBus();
        model.addAttribute("busList", busList);
        return "bus/buses";
    }

    @GetMapping("/buses/update")
    public String update(@RequestParam("id") Integer id, Model model) {
        Bus bus = busService.getBusById(id);
        model.addAttribute("bus", bus);
        return "/bus/bus-update";
    }

    @PostMapping("/buses/update")
    public String save(@ModelAttribute("driver") Bus bus, Model model) {
        busService.updateBus(bus);
        List<Bus> busList = busService.getAllBus();
        model.addAttribute("busList", busList);
        return "bus/buses";
    }

    @GetMapping("/buses/delete")
    public String delete(@RequestParam("id") Integer id, Model model) {
        busService.deleteBusById(id);
        List<Bus> busList = busService.getAllBus();
        model.addAttribute("busList", busList);
        return "bus/buses";
    }

}
