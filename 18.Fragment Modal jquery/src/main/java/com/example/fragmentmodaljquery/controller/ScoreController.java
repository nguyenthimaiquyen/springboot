package com.example.fragmentmodaljquery.controller;

import com.example.fragmentmodaljquery.exception.StudentNotFoundException;
import com.example.fragmentmodaljquery.exception.SubjectNotFoundException;
import com.example.fragmentmodaljquery.model.request.ScoreCreationRequest;
import com.example.fragmentmodaljquery.model.request.SubjectCreationRequest;
import com.example.fragmentmodaljquery.model.response.ScoreDetailResponse;
import com.example.fragmentmodaljquery.model.response.StudentDetailResponse;
import com.example.fragmentmodaljquery.model.response.SubjectDetailResponse;
import com.example.fragmentmodaljquery.service.ScoreService;
import com.example.fragmentmodaljquery.service.StudentService;
import com.example.fragmentmodaljquery.service.SubjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ScoreController {
    public final StudentService studentService;
    public final SubjectService subjectService;
    public final ScoreService scoreService;

    @GetMapping("/scores")
    public String getAll(Model model) {
        List<ScoreDetailResponse> scores = scoreService.getAll();
        model.addAttribute("scores", scores);
        return "score/scores";
    }

    @GetMapping("/scores/students")
    public ResponseEntity<?> getStudent() throws StudentNotFoundException {
        return ResponseEntity.ok(studentService.getAll());
    }

    @GetMapping("/scores/subjects")
    public ResponseEntity<?> getSubject() throws SubjectNotFoundException {
        return ResponseEntity.ok(subjectService.getAll());
    }

    @PostMapping("/scores")
    public ResponseEntity<?> create(@RequestBody ScoreCreationRequest request) throws SubjectNotFoundException, StudentNotFoundException {
        scoreService.create(request);
        return ResponseEntity.ok(null);
    }




}
