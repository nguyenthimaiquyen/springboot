package com.quyen.qlhd.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceCreationRequest {

    @NotNull(message = "Tên khách hàng bắt buộc chọn")
    @Min(value = 1, message = "ID không âm")
    private Integer customerId;

    @NotNull(message = "Tên dịch vụ bắt buộc chọn")
    @Min(value = 1, message = "ID không âm")
    private Integer serviceId;


    @NotNull(message = "Ngày đăng ký bắt buộc nhập")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate registerDate;

    @NotNull(message = "Ngày mở rộng bắt buộc nhập")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate extensionDate;
}
