package com.quyen.test.repository;

import com.quyen.test.entity.Appointment;
import com.quyen.test.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductJpaRepository extends JpaRepository<Product, Long> {
}
