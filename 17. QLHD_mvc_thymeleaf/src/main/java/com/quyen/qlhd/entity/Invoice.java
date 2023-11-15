package com.quyen.qlhd.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Invoice {
    private int id;
    private Customer customer;
    private Service service;
    private LocalDate registerDate;
    private LocalDate extensionDate;
}
