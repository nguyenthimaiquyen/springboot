package com.quyen.test.controller;

import com.quyen.test.model.response.ProductDetailResponse;
import com.quyen.test.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@AllArgsConstructor
@RequestMapping
public class MenuController {
    private final ProductService productService;

    @GetMapping("/home")
    public String home(Model model) {
        List<ProductDetailResponse> products = productService.getAll();
        model.addAttribute("products", products);
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
