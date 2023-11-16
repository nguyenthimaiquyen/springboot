package com.quyen.springthymeleaf.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssignmentCreationRequest {
    @NotNull(message = "Tài xê bắt buộc chọn")
    @Min(value = 1, message = "Không nhập ID tài xế là số âm")
    private Integer driverId;

    @NotNull(message = "Tuyến xe bus bắt buộc chọn")
    @Min(value = 1, message = "Không nhập ID tuyến xe bus là số âm")
    private Integer busId;

    @NotNull(message = "Số lượt lái xe bắt buộc chọn")
    @Min(value = 1, message = "Không nhập số lượt lái xe là số âm")
    private Integer driving;

    @NotNull(message = "Thời gian phân công bắt buộc chọn")
    @Min(value = 1, message = "Không nhập thời gian phân công (tháng) là số âm")
    private Integer assignmentTime;

}
