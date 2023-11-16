package com.quyen.springthymeleaf.model.request;

import com.quyen.springthymeleaf.statics.Level;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.*;

@Data
public class DriverUpdateRequest {
    @NotNull(message = "ID bắt buộc phải có")
    @Min(value = 1, message = "ID không âm")
    private Integer id;

    @NotBlank(message = "Tên tài xế bắt buộc nhập")
    @Length(max = 100, message = "Tên tài xế không được vượt quá 100 kí tự")
    private String name;

    @NotBlank(message = "Địa chỉ bắt buộc nhập")
    @Length(max = 200, message = "Địa chỉ không được vượt quá 200 kí tự")
    private String address;

    @Length(min = 10, max = 10, message = "Số điện thoại có 10 kí tự")
    private String phone;

    @NotNull(message = "Trình độ của tài xế bắt buộc chọn")
    private Level level;

}
