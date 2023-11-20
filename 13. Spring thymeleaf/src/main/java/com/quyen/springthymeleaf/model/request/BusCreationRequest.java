package com.quyen.springthymeleaf.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusCreationRequest {

    @NotNull(message = "Khoảng cách bắt buộc nhập")
    @Min(value = 1, message = "Khoảng cách không âm")
    private Integer distance;

    @NotNull(message = "Số điểm dừng xe buýt bắt buộc nhập")
    @Min(value = 1, message = "Số điểm dừng xe buýt không âm")
    private Integer busStop;

}
