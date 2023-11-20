package com.quyen.qlhd.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private LocalDateTime registerDate;
    private LocalDateTime extensionDate;

    public Invoice(Customer customer, Service service, LocalDateTime registerDate, LocalDateTime extensionDate) {
        this.customer = customer;
        this.service = service;
        this.registerDate = registerDate;
        this.extensionDate = extensionDate;
    }
}
