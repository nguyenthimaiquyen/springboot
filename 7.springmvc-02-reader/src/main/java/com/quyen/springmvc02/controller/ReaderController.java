package com.quyen.springmvc02.controller;

import com.quyen.springmvc02.entity.Reader;
import com.quyen.springmvc02.model.request.ReaderCreationRequest;
import com.quyen.springmvc02.service.ReaderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class ReaderController {

    private final ReaderService readerService;

    @GetMapping("/readers")
    public String home(Model model) {
        List<Reader> readers = readerService.getAll();
        model.addAttribute("readers", readers);
        return "readers";
    }

    @GetMapping("/create-reader")
    public String forwardToReaderCreation(Model model) {
        model.addAttribute("ReaderWantToAdd", new ReaderCreationRequest());
        return "reader-creation";
    }

    @GetMapping("/delete-reader/{id}")
    public String deleteReader(@PathVariable("id") int id, Model model) {
        readerService.deleteReader(id);
        List<Reader> readers = readerService.getAll();
        model.addAttribute("readers", readers);
        return "readers";
    }

    @PostMapping("/create-reader")
    public String createReader(@ModelAttribute("ReaderWantToAdd") ReaderCreationRequest reader, Model model) {
        readerService.createReader(reader);
        List<Reader> readers = readerService.getAll();
        model.addAttribute("readers", readers);
        return "readers";
    }
}
