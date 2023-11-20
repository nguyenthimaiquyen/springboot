package com.quyen.springthymeleaf.controller;

import com.quyen.springthymeleaf.entity.Assignment;
import com.quyen.springthymeleaf.entity.Bus;
import com.quyen.springthymeleaf.entity.Driver;
import com.quyen.springthymeleaf.exception.BusNotFoundException;
import com.quyen.springthymeleaf.exception.DriverNotFoundException;
import com.quyen.springthymeleaf.model.request.AssignmentCreationRequest;
import com.quyen.springthymeleaf.service.AssignmentService;
import com.quyen.springthymeleaf.service.BusService;
import com.quyen.springthymeleaf.service.DriverService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@AllArgsConstructor
public class AssignmentController {
    private final AssignmentService assignmentService;
    private final DriverService driverService;
    private final BusService busService;

    @GetMapping("/assignment")
    public String home(Model model) {
        List<Assignment> assignmentList = assignmentService.getAll();
        model.addAttribute("assignmentList", assignmentList);
        return "assignment/assignments";
    }

    @GetMapping("/assignment/create")
    public String initAssignment(Model model) {
        List<Driver> driverList = driverService.getAll();
        model.addAttribute("driverList", driverList);

        List<Bus> busList = busService.getAll();
        model.addAttribute("busList", busList);

        model.addAttribute("assignment", new AssignmentCreationRequest());
        return "/assignment/assignment-creation";
    }

    @PostMapping("/assignment/create")
    public String create(@ModelAttribute("assignment") @Valid AssignmentCreationRequest assignment,
                         Errors errors) throws DriverNotFoundException, BusNotFoundException {
        if (errors !=  null && errors.getErrorCount() > 0) {
            return "/assignment/assignment-creation";
        }
        List<Assignment>  assignmentList = assignmentService.create(assignment);
        return "redirect:/assignment";
    }


}
