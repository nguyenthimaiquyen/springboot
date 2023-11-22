package com.quyen.qlhd.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Invoice {
    private int id;
    private Customer customer;
    private Service service;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate registerDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate extensionDate;

    public Invoice(Customer customer, Service service, LocalDate registerDate, LocalDate extensionDate) {
        this.customer = customer;
        this.service = service;
        this.registerDate = registerDate;
        this.extensionDate = extensionDate;
    }
}
