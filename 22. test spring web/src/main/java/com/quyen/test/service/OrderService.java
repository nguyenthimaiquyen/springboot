package com.quyen.test.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quyen.test.entity.Order;
import com.quyen.test.model.request.OrderRequest;
import com.quyen.test.repository.OrderJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class OrderService {
    private final ObjectMapper objectMapper;
    private final OrderJpaRepository orderJpaRepository;
    private final EmailService emailService;

    public void saveOrder(OrderRequest request) {
        Order order = objectMapper.convertValue(request, Order.class);
        order.setCreatedAt(LocalDateTime.now());
        orderJpaRepository.save(order);
    }

    public void sendOrderMail(OrderRequest request) throws MessagingException {
        String email = request.getEmail();
        String name = request.getName();
        emailService.sendOrderMail(name, email);
    }
}
