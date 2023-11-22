package com.example.fragmentmodaljquery.model.request;

import com.example.fragmentmodaljquery.entity.Student;
import com.example.fragmentmodaljquery.entity.Subject;
import com.example.fragmentmodaljquery.statics.SubjectType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScoreCreationRequest {

    @NotNull(message = "Sinh viên bắt buộc có")
    @Min(value = 1, message = "ID sinh viên không âm")
    private Integer studentId;

    @NotNull(message = "Môn học bắt buộc có")
    @Min(value = 1, message = "ID môn học không âm")
    private Integer subjectId;

    @NotNull(message = "Ngày thi bắt buộc nhập")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate testDate;

    @NotNull(message = "Điểm bắt buộc có")
    @Min(value = 1, message = "Điểm không âm")
    private Double score;
}
