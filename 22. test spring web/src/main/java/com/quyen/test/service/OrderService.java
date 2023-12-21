package com.quyen.test.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quyen.test.entity.Order;
import com.quyen.test.entity.Product;
import com.quyen.test.model.request.OrderRequest;
import com.quyen.test.repository.OrderJpaRepository;
import com.quyen.test.repository.ProductJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderService {
    private final ObjectMapper objectMapper;
    private final OrderJpaRepository orderJpaRepository;
    private final EmailService emailService;
    private final ProductJpaRepository productJpaRepository;

    public void saveOrder(OrderRequest request, Long id) {
        Order order = objectMapper.convertValue(request, Order.class);
        Product orderedProduct = productJpaRepository.findById(id).get();
        order.setCreatedAt(LocalDateTime.now());
        order.setProduct(orderedProduct);
        orderJpaRepository.save(order);
    }

    public void sendOrderMail(OrderRequest request) throws MessagingException {
        String email = request.getEmail();
        String name = request.getName();
        emailService.sendOrderMail(name, email);
    }
}
