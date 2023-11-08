package com.quyen.springthymeleaf.controller;

import com.quyen.springthymeleaf.entity.Driver;
import com.quyen.springthymeleaf.service.DriverService;
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
public class DriverController {
    private final DriverService driverService;

    @GetMapping("/")
    public String home(Model model) {
        List<Driver> driverList = driverService.getAllDriver();
        model.addAttribute("driverList", driverList);
        return "driver/drivers";
    }

    @GetMapping("/drivers/create")
    public String create(Model model) {
        Driver newDriver = new Driver();
        model.addAttribute("driver", newDriver);
        return "/driver/driver-creation";
    }

    @PostMapping("/drivers/create")
    public String create(@ModelAttribute("driver") Driver driver, Model model) {
        driverService.create(driver);
        List<Driver> driverList = driverService.getAllDriver();
        model.addAttribute("driverList", driverList);
        return "driver/drivers";
    }

    @GetMapping("/drivers/update")
    public String update(@RequestParam("id") Integer id, Model model) {
        Driver driver = driverService.getDriverById(id);
        model.addAttribute("driver", driver);
        return "/driver/driver-update";
    }

    @PostMapping("/drivers/update")
    public String save(@ModelAttribute("driver") Driver driver, Model model) {
        driverService.updateDriver(driver);
        List<Driver> driverList = driverService.getAllDriver();
        model.addAttribute("driverList", driverList);
        return "driver/drivers";
    }

    @GetMapping("/drivers/delete")
    public String delete(@RequestParam("id") Integer id, Model model) {
        driverService.deleteDriverById(id);
        List<Driver> driverList = driverService.getAllDriver();
        model.addAttribute("driverList", driverList);
        return "driver/drivers";
    }
}
