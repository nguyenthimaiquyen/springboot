package com.quyen.test.controller;

import com.google.gson.Gson;
import com.quyen.test.exception.ProductNotFoundException;
import com.quyen.test.model.request.ProductRequest;
import com.quyen.test.model.response.ProductResponse;
import com.quyen.test.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/admin")
public class ProductController {
    private final ProductService productService;
    private final Gson gson;

    @GetMapping
    public String getAllProduct(Model model) {
        List<ProductResponse> products = productService.getAll();
        model.addAttribute("products", products);
        return "product/products";
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<?> getProductDetails(@PathVariable Long id) throws ProductNotFoundException {
        ProductResponse product = productService.getProductDetails(id);
        return ResponseEntity.ok(product);
    }

    @PostMapping("/products")
    public ResponseEntity<?> create(@RequestPart("productRequest") String productRequest,
                                    @RequestPart("image") MultipartFile image) {
        ProductRequest request = gson.fromJson(productRequest, ProductRequest.class);
        productService.create(request, image);
        return ResponseEntity.ok(null);
    }


    @PutMapping("/products")
    public ResponseEntity<?> update(@RequestBody ProductRequest request) {
        productService.save(request);
        return ResponseEntity.ok(null);
    }

}
