package com.quyen.qlhd.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceCreationRequest {
    private int customerId;
    private int serviceId;
    private LocalDate registerDate;
    private LocalDate extensionDate;
}
