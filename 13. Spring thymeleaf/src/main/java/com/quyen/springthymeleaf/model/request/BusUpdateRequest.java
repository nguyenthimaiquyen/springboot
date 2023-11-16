package com.quyen.springthymeleaf.model.request;


import lombok.Data;
import javax.validation.constraints.*;

@Data
public class BusUpdateRequest {
    @NotNull(message = "ID bắt buộc phải có")
    @Min(value = 1, message = "ID không âm")
    private Integer id;

    @NotNull(message = "Khoảng cách bắt buộc nhập")
    @Min(value = 1, message = "Khoảng cách không âm")
    private Integer distance;

    @NotNull(message = "Số điểm dừng xe buýt bắt buộc nhập")
    @Min(value = 1, message = "Số điểm dừng xe buýt không âm")
    private Integer busStop;

}
