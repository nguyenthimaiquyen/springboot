package com.example.fragmentmodaljquery.controller;

import com.example.fragmentmodaljquery.entity.Subject;
import com.example.fragmentmodaljquery.exception.SubjectNotFoundException;
import com.example.fragmentmodaljquery.model.request.SubjectCreationRequest;
import com.example.fragmentmodaljquery.model.request.SubjectUpdateRequest;
import com.example.fragmentmodaljquery.model.response.SubjectDetailResponse;
import com.example.fragmentmodaljquery.service.SubjectService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class SubjectController {
    private final SubjectService subjectService;

    @GetMapping("/subjects")
    public String home(Model model) {
        List<Subject> subjects = subjectService.getAll();
        model.addAttribute("subjects", subjects);
        return "subject/subjects";
    }

    @GetMapping("/delete-subject/{id}")
    public String delete(@PathVariable("id") int id, Model model) throws SubjectNotFoundException {
        List<Subject> subjects = subjectService.delete(id);
        model.addAttribute("subjects", subjects);
        return "subject/subjects";
    }

    @GetMapping("/create-subject")
    public String forwardToSubjecteCreation(Model model) {
        model.addAttribute("SubjectCreationRequest", new SubjectCreationRequest());
        return "subject/subject-creation";
    }

    @PostMapping("/create-subject")
    public String create(@ModelAttribute("SubjectCreationRequest") @Valid SubjectCreationRequest subject,
                                Errors errors) {
        if (errors !=  null && errors.getErrorCount() > 0) {
            return "subject/service-creation";
        }
        List<Subject> subjects = subjectService.create(subject);
        return "redirect:/subjects";
    }

    @GetMapping("/update-subject/{subject-id}")
    public String forwardToSubjectUpdate(@PathVariable("subject-id") int id, Model model) throws SubjectNotFoundException {
        SubjectDetailResponse subject = subjectService.findById(id);
        model.addAttribute("SubjectUpdateRequest", subject);
        return "subject/subject-update";
    }

    @PostMapping("/update-subject")
    public String update(@ModelAttribute("SubjectUpdateRequest") @Valid SubjectUpdateRequest subject,
                                Errors errors) throws SubjectNotFoundException {
        if (errors !=  null && errors.getErrorCount() > 0) {
            return "subject/subject-update";
        }
        List<Subject> subjects = subjectService.update(subject);
        return "redirect:/subjects";
    }

}
