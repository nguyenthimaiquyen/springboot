package com.example.fragmentmodaljquery.controller;

import com.example.fragmentmodaljquery.entity.Student;
import com.example.fragmentmodaljquery.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping("/")
    public String getTasks(Model model) {
        List<Student> students = studentService.getAll();
        model.addAttribute("students", students);
        return "index";
    }




}
