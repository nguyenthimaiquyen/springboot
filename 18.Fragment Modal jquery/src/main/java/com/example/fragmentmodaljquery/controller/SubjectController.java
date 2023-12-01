package com.example.fragmentmodaljquery.controller;

import com.example.fragmentmodaljquery.exception.SubjectNotFoundException;
import com.example.fragmentmodaljquery.model.request.SubjectRequest;
import com.example.fragmentmodaljquery.model.response.SubjectDetailResponse;
import com.example.fragmentmodaljquery.service.SubjectService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
public class SubjectController {
    private final SubjectService subjectService;

    @GetMapping("/subjects")
    public String home(Model model) throws SubjectNotFoundException {
        List<SubjectDetailResponse> subjects = subjectService.getAll();
        model.addAttribute("subjects", subjects);
        return "subject/subjects";
    }

    @GetMapping("/subjects/type")
    public ResponseEntity<?> getSubjectType() {
        return ResponseEntity.ok(subjectService.getSubjectType());
    }

    @GetMapping("/subjects/{id}")
    public ResponseEntity<?> getSubjectDetails(@PathVariable Integer id) throws SubjectNotFoundException {
        SubjectDetailResponse subject = subjectService.getSubjectDetails(id);
        return ResponseEntity.ok(subject);
    }

    @PostMapping("/subjects")
    public ResponseEntity<?> create(@RequestBody SubjectRequest request) throws SubjectNotFoundException {
        subjectService.save(request);
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/subjects/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) throws SubjectNotFoundException {
        subjectService.delete(id);
        return ResponseEntity.ok(null);
    }

    @PutMapping("/subjects")
    public ResponseEntity<?> update(@RequestBody SubjectRequest request) throws SubjectNotFoundException {
        subjectService.save(request);
        return ResponseEntity.ok(null);
    }


}
