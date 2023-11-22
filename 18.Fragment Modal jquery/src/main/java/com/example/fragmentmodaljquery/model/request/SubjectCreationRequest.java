package com.example.fragmentmodaljquery.model.request;

import com.example.fragmentmodaljquery.statics.SubjectType;
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
public class SubjectCreationRequest {
    @NotBlank(message = "Tên môn học bắt buộc nhập")
    @Length(max = 100, message = "Tên môn học không được vượt quá 100 kí tự")
    private String subjectName;

    @NotNull(message = "Số tín chỉ bắt buộc có")
    @Min(value = 1, message = "Số tín chỉ không âm")
    private Integer credit;

    @NotNull(message = "Loại môn học bắt buộc chọn")
    private SubjectType subjectType;
}
