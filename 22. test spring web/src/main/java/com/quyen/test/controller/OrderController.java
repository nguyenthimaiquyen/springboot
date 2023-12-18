package com.quyen.test.controller;

import com.quyen.test.model.request.OrderRequest;
import com.quyen.test.service.EmailService;
import com.quyen.test.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.mail.MessagingException;

@Controller
@AllArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<?> saveOrder(@RequestBody OrderRequest request) throws MessagingException {
        orderService.saveOrder(request);
        orderService.sendOrderMail(request);
        return ResponseEntity.ok(null);
    }
}
