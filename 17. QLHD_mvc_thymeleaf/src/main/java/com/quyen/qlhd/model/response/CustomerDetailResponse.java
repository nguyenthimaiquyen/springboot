package com.quyen.qlhd.model.response;

import com.quyen.qlhd.statics.CustomerType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@Builder
public class CustomerDetailResponse {

    @NotNull(message = "ID bắt buộc phải có")
    @Min(value = 1, message = "ID không âm")
    private int id;

    @NotBlank(message = "Tên khách hàng bắt buộc nhập")
    @Length(max = 100, message = "Tên khách hàng không được vượt quá 100 kí tự")
    private String fullname;

    @NotBlank(message = "Địa chỉ bắt buộc nhập")
    @Length(max = 100, message = "Địa chỉ không được vượt quá 100 kí tự")
    private String address;

    @NotBlank(message = "Số điện thoại bắt buộc nhập")
    @Length(max = 100, message = "Số điện thoại không được vượt quá 100 kí tự")
    private String phone;

    @NotNull(message = "Kiểu khách hàng bắt buộc chọn")
    private CustomerType customerType;

}
