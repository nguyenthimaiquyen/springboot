package com.quyen.springthymeleaf.controller;

import com.quyen.springthymeleaf.entity.Bus;
import com.quyen.springthymeleaf.exception.BusNotFoundException;
import com.quyen.springthymeleaf.model.request.BusCreationRequest;
import com.quyen.springthymeleaf.model.request.BusUpdateRequest;
import com.quyen.springthymeleaf.model.response.BusDetailResponse;
import com.quyen.springthymeleaf.service.BusService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@AllArgsConstructor
public class BusController {
    private final BusService busService;

    @GetMapping("/buses")
    public String bus(Model model) {
        List<Bus> busList = busService.getAll();
        model.addAttribute("busList", busList);
        return "bus/buses";
    }

    @GetMapping("/buses/create")
    public String create(Model model) {
        model.addAttribute("bus", new BusCreationRequest());
        return "bus/bus-creation";
    }

    @PostMapping("/buses/create")
    public String create(@ModelAttribute("bus") @Valid BusCreationRequest bus, Errors errors) {
        if (null != errors && errors.getErrorCount() > 0) {
            return "/bus/bus-creation";
        }
        List<Bus> busList = busService.create(bus);
//        model.addAttribute("busList", busList);
        return "redirect:/buses";
    }

    @GetMapping("/buses/update/{id}")
    public String update(@PathVariable("id") int id, Model model) throws BusNotFoundException {
        BusDetailResponse bus = busService.getById(id);
        model.addAttribute("bus", bus);
        return "/bus/bus-update";
    }

    @PostMapping("/buses/update")
    public String update(@ModelAttribute("bus") @Valid BusUpdateRequest bus, Errors errors) throws BusNotFoundException {
        if (null != errors && errors.getErrorCount() > 0) {
            return "/bus/bus-update";
        }
        List<Bus> busList = busService.update(bus);
//        model.addAttribute("busList", busList);
        return "redirect:/buses";
    }

    @GetMapping("/buses/delete/{id}")
    public String delete(@PathVariable("id") int id, Model model) throws BusNotFoundException {
        List<Bus> busList = busService.deleteById(id);
        model.addAttribute("busList", busList);
        return "bus/buses";
    }

}
