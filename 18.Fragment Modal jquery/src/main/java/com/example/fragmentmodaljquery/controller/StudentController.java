package com.example.fragmentmodaljquery.controller;

import com.example.fragmentmodaljquery.exception.StudentNotFoundException;
import com.example.fragmentmodaljquery.model.request.StudentCreationRequest;
import com.example.fragmentmodaljquery.model.request.StudentUpdateRequest;
import com.example.fragmentmodaljquery.model.response.StudentDetailResponse;
import com.example.fragmentmodaljquery.service.StudentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
@AllArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping("/")
    public String getAllStudent(Model model) throws StudentNotFoundException {
        List<StudentDetailResponse> students = studentService.getAll();
        model.addAttribute("students", students);
        return "index";
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<?> getStudentDetails(@PathVariable Integer id) throws StudentNotFoundException {
        StudentDetailResponse student = studentService.getStudentDetails(id);
        return ResponseEntity.ok(student);
    }

    @PostMapping("/students")
    public ResponseEntity<?> create(@RequestBody StudentCreationRequest request) {
        studentService.create(request);
        return ResponseEntity.ok(null);
    }


    @DeleteMapping("/students/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) throws StudentNotFoundException {
        studentService.delete(id);
        return ResponseEntity.ok(null);
    }

    @PutMapping("/students/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id,
                                    @RequestBody StudentUpdateRequest request) throws StudentNotFoundException {
        studentService.update(id, request);
        return ResponseEntity.ok(null);
    }

}
