package com.quyen.test.service;

import com.quyen.test.entity.Order;
import com.quyen.test.entity.Product;
import com.quyen.test.model.request.OrderRequest;
import com.quyen.test.repository.OrderJpaRepository;
import com.quyen.test.repository.ProductJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderJpaRepository orderJpaRepository;
    private final EmailService emailService;
    private final ProductJpaRepository productJpaRepository;

    public void saveOrder(OrderRequest request, Long id) {
        Order order = Order.builder()
                .name(request.getName())
                .phone(request.getPhone())
                .email(request.getEmail())
                .build();
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
