package com.example.fragmentmodaljquery.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentRequest {

    private Long id;

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
