package com.quyen.springthymeleaf.controller;

import com.quyen.springthymeleaf.entity.Driver;
import com.quyen.springthymeleaf.exception.DriverNotFoundException;
import com.quyen.springthymeleaf.model.request.DriverCreationRequest;
import com.quyen.springthymeleaf.model.request.DriverUpdateRequest;
import com.quyen.springthymeleaf.model.response.DriverDetailResponse;
import com.quyen.springthymeleaf.service.DriverService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Controller
@AllArgsConstructor
public class DriverController {
    private final DriverService driverService;

    @GetMapping("/")
    public String home(Model model) {
        List<Driver> driverList = driverService.getAll();
        model.addAttribute("driverList", driverList);
        return "driver/drivers";
    }

    @GetMapping("/drivers/create")
    public String create(Model model) {
        model.addAttribute("driver", new DriverCreationRequest());
        return "/driver/driver-creation";
    }

    @PostMapping("/drivers/create")
    public String create(@ModelAttribute("driver") @Valid DriverCreationRequest driver, Errors errors, Model model) {
        if (errors !=  null && errors.getErrorCount() > 0) {
            return "/driver/driver-creation";
        }
        List<Driver> driverList = driverService.create(driver);
        model.addAttribute("driverList", driverList);
        return "driver/drivers";
    }

    @GetMapping("/drivers/update/{id}")
    public String update(@PathVariable("id") int id, Model model) throws DriverNotFoundException {
        DriverDetailResponse driver = driverService.getById(id);
        model.addAttribute("driver", driver);
        return "/driver/driver-update";
    }

    @PostMapping("/drivers/update")
    public String update(@ModelAttribute("driver") @Valid DriverUpdateRequest driver, Errors errors, Model model) throws DriverNotFoundException {
        if (errors !=  null && errors.getErrorCount() > 0) {
            return "/driver/driver-creation";
        }
        List<Driver> driverList = driverService.update(driver);
        model.addAttribute("driverList", driverList);
        return "driver/drivers";
    }

    @GetMapping("/drivers/delete/{id}")
    public String delete(@PathVariable("id") int id, Model model) throws DriverNotFoundException {
        List<Driver> driverList = driverService.deleteById(id);
        model.addAttribute("driverList", driverList);
        return "driver/drivers";
    }



}
