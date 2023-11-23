package com.example.fragmentmodaljquery.controller;

import com.example.fragmentmodaljquery.entity.Student;
import com.example.fragmentmodaljquery.model.request.StudentCreationRequest;
import com.example.fragmentmodaljquery.service.StudentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.List;

@Controller
@AllArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping("/")
    public String getAllStudent(Model model) {
        List<Student> students = studentService.getAll();
        model.addAttribute("students", students);
        return "index";
    }

    @PostMapping("/students")
    public ResponseEntity<?> create(@RequestBody StudentCreationRequest request) {
        studentService.create(request);
        return ResponseEntity.ok(null);
    }




}
