package com.quyen.springthymeleaf.controller;

import com.quyen.springthymeleaf.entity.Assignment;
import com.quyen.springthymeleaf.service.AssignmentService;
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
public class AssignmentController {
    private final AssignmentService assignmentService;

    @GetMapping("/assignment")
    public String home(Model model) {
        List<Assignment> assignmentList = assignmentService.getAllAssignment();
        model.addAttribute("assignmentList", assignmentList);
        return "assignment/assignments";
    }

    @GetMapping("/assignment/create")
    public String create(Model model) {
        Assignment newAssignment = new Assignment();
        model.addAttribute("assignment", newAssignment);
        return "/assignment/assignment-creation";
    }

    @PostMapping("/assignment/create")
    public String create(@ModelAttribute("assignment") Assignment assignment, Model model) {
        assignmentService.create(assignment);
        List<Assignment>  assignmentList = assignmentService.getAllAssignment();
        model.addAttribute("assignmentList", assignmentList);
        return "assignment/assignments";
    }

    @GetMapping("/assignment/update")
    public String update(@RequestParam("id") Integer id, Model model) {
        Assignment assignment = assignmentService.getAssignmentById(id);
        model.addAttribute("assignment", assignment);
        return "/assignment/assignment-update";
    }

    @PostMapping("/assignment/update")
    public String save(@ModelAttribute("assignment") Assignment assignment, Model model) {
        assignmentService.updateAssignment(assignment);
        List<Assignment>  assignmentList = assignmentService.getAllAssignment();
        model.addAttribute("assignmentList", assignmentList);
        return "assignment/assignments";
    }

    @GetMapping("/assignment/delete")
    public String delete(@RequestParam("id") Integer id, Model model) {
        assignmentService.deleteAssignmentById(id);
        List<Assignment>  assignmentList = assignmentService.getAllAssignment();
        model.addAttribute("assignmentList", assignmentList);
        return "assignment/assignments";
    }

}
