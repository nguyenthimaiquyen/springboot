package com.example.fragmentmodaljquery.controller;

import com.example.fragmentmodaljquery.entity.Student;
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

    @GetMapping("/update-student/{student-id}")
    public String forwardToSubjectUpdate(@PathVariable("student-id") int id, Model model) throws StudentNotFoundException {
        StudentDetailResponse student = studentService.findById(id);
        model.addAttribute("StudentUpdateRequest", student);
        return "student/student-update";
    }

    @PostMapping("/update-student")
    public String update(@ModelAttribute("StudentUpdateRequest") @Valid StudentUpdateRequest request,
                         Errors errors) throws StudentNotFoundException {
        if (errors !=  null && errors.getErrorCount() > 0) {
            return "student/student-update";
        }
        List<Student> students = studentService.update(request);
        return "redirect:/";
    }

    @GetMapping("/delete-student/{id}")
    public String delete(@PathVariable("id") int id, Model model) throws StudentNotFoundException {
        List<Student> students = studentService.delete(id);
        model.addAttribute("students", students);
        return "index";
    }

}
