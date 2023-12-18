package com.quyen.test.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@AllArgsConstructor
@RequestMapping
public class MenuController {

    @GetMapping("/home")
    public String getAllPet() {
        return "index";
    }

    @GetMapping("/contact")
    public String getContact() {
        return "/drool-html/contact";
    }

    @GetMapping("/about")
    public String getAbout() {
        return "/drool-html/about";
    }




}
