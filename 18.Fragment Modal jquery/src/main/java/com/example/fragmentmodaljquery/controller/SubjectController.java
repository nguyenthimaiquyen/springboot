package com.example.fragmentmodaljquery.controller;

import com.example.fragmentmodaljquery.exception.SubjectNotFoundException;
import com.example.fragmentmodaljquery.model.request.SearchSubjectRequest;
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
@RequestMapping("/subjects")
public class SubjectController {
    private final SubjectService subjectService;

    @GetMapping
    public String searchSubject(Model model, SearchSubjectRequest request) {
        List<SubjectDetailResponse> subjects = subjectService.searchSubject(request);
        model.addAttribute("subjects", subjects);
        return "subject/subjects";
    }

    @GetMapping("/type")
    public ResponseEntity<?> getSubjectType() {
        return ResponseEntity.ok(subjectService.getSubjectType());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSubjectDetails(@PathVariable Long id) throws SubjectNotFoundException {
        SubjectDetailResponse subject = subjectService.getSubjectDetails(id);
        return ResponseEntity.ok(subject);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody SubjectRequest request) throws SubjectNotFoundException {
        subjectService.save(request);
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws SubjectNotFoundException {
        subjectService.delete(id);
        return ResponseEntity.ok(null);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody SubjectRequest request) throws SubjectNotFoundException {
        subjectService.save(request);
        return ResponseEntity.ok(null);
    }


}
