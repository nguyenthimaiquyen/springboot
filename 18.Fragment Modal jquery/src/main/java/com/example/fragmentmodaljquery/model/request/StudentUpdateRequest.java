package com.example.fragmentmodaljquery.model.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentUpdateRequest {

    @NotNull(message = "ID bắt buộc phải có")
    @Min(value = 1, message = "ID không âm")
    private Integer id;

    @NotBlank(message = "Tên sinh viên bắt buộc nhập")
    @Length(max = 100, message = "Tên sinh viên không được vượt quá 100 kí tự")
    private String name;

    @NotBlank(message = "Địa chỉ bắt buộc nhập")
    @Length(max = 200, message = "Địa chỉ không được vượt quá 200 kí tự")
    private String address;

    @NotBlank(message = "Số điện thoại bắt buộc nhập")
    @Length(max = 10, message = "Số điện thoại không được vượt quá 10 kí tự")
    private String phone;

    @NotBlank(message = "Tên lớp bắt buộc nhập")
    @Length(max = 100, message = "Tên lớp không được vượt quá 100 kí tự")
    private String className;
}
