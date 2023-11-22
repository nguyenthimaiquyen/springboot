package com.example.fragmentmodaljquery.controller;

import com.example.fragmentmodaljquery.entity.Score;
import com.example.fragmentmodaljquery.entity.Student;
import com.example.fragmentmodaljquery.entity.Subject;
import com.example.fragmentmodaljquery.exception.StudentNotFoundException;
import com.example.fragmentmodaljquery.exception.SubjectNotFoundException;
import com.example.fragmentmodaljquery.model.request.ScoreCreationRequest;
import com.example.fragmentmodaljquery.service.ScoreService;
import com.example.fragmentmodaljquery.service.StudentService;
import com.example.fragmentmodaljquery.service.SubjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ScoreController {
    public final StudentService studentService;
    public final SubjectService subjectService;
    public final ScoreService scoreService;


    @GetMapping("/scores")
    public String getAll(Model model) {
        List<Score> scores = scoreService.getAll();
        model.addAttribute("scores", scores);
        return "score/scores";
    }

    @GetMapping("/create-score")
    public String init(Model model) {
        List<Student> students = studentService.getAll();
        model.addAttribute("customers", students);

        List<Subject> subjects = subjectService.getAll();
        model.addAttribute("services", subjects);

        model.addAttribute("ScoreCreationRequest", new ScoreCreationRequest());
        return "score/score-creation";
    }

    @PostMapping("/create-score")
    public String create(@ModelAttribute("ScoreCreationRequest") @Valid ScoreCreationRequest request,
                                Errors errors) throws SubjectNotFoundException, StudentNotFoundException {
        if (errors !=  null && errors.getErrorCount() > 0) {
            return "score/score-creation";
        }
        List<Score> scores = scoreService.create(request);
//        model.addAttribute("invoices", invoices);
        return "redirect:/scores";
    }



}
