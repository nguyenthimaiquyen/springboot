package com.quyen.qlhd.model.request;

import com.quyen.qlhd.statics.Unit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ServiceCreationRequest {
    @NotBlank(message = "Tên dịch vụ bắt buộc nhập")
    @Length(max = 100, message = "Tên dịch vụ không được vượt quá 100 kí tự")
    private String name;

    @NotNull(message = "Giá dịch vụ bắt buộc có")
    @Min(value = 1, message = "Giá dịch vụ không âm")
    private Double price;

    @NotNull(message = "Đơn vị tính dịch vụ bắt buộc chọn")
    private Unit unit;

    @NotNull(message = "Kì hạn dịch vụ bắt buộc có")
    @Min(value = 1, message = "Kì hạn dịch vụ không âm")
    private Double period;

}
