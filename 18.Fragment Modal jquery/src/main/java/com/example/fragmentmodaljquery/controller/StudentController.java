package com.example.fragmentmodaljquery.controller;

import com.example.fragmentmodaljquery.exception.StudentNotFoundException;
import com.example.fragmentmodaljquery.model.request.StudentRequest;
import com.example.fragmentmodaljquery.model.response.StudentDetailResponse;
import com.example.fragmentmodaljquery.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    @GetMapping
    public String getAllStudent(Model model) {
        List<StudentDetailResponse> students = studentService.getAll();
        model.addAttribute("students", students);
        return "index";
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentDetails(@PathVariable Long id) throws StudentNotFoundException {
        StudentDetailResponse student = studentService.getStudentDetails(id);
        return ResponseEntity.ok(student);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody StudentRequest request) {
        studentService.save(request);
        return ResponseEntity.ok(null);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) throws StudentNotFoundException {
        studentService.delete(id);
        return ResponseEntity.ok(null);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody StudentRequest request) {
        studentService.save(request);
        return ResponseEntity.ok(null);
    }

}
