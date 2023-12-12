package com.example.fragmentmodaljquery.model.request;

import com.example.fragmentmodaljquery.statics.SubjectType;
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
public class SubjectRequest {
    @NotNull(message = "ID bắt buộc phải có")
    @Min(value = 1, message = "ID không âm")
    private Long id;

    @NotBlank(message = "Tên môn học bắt buộc nhập")
    @Length(max = 100, message = "Tên môn học không được vượt quá 100 kí tự")
    private String subjectName;

    @NotNull(message = "Số tín chỉ bắt buộc có")
    @Min(value = 1, message = "Số tín chỉ không âm")
    private Integer credit;

    @NotNull(message = "Loại môn học bắt buộc chọn")
    private SubjectType subjectType;
}
