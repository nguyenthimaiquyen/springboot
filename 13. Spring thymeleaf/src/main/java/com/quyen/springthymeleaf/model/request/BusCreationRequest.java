package com.quyen.springthymeleaf.model.request;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class BusCreationRequest {

    @NotNull(message = "Khoảng cách bắt buộc nhập")
    @Min(value = 1, message = "Khoảng cách không âm")
    private Integer distance;

    @NotNull(message = "Số điểm dừng xe buýt bắt buộc nhập")
    @Min(value = 1, message = "Số điểm dừng xe buýt không âm")
    private Integer busStop;

}
