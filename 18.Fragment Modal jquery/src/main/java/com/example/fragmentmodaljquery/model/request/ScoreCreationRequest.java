package com.example.fragmentmodaljquery.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScoreCreationRequest {

    @NotNull(message = "Sinh viên bắt buộc có")
    @Min(value = 1, message = "ID sinh viên không âm")
    private Long studentId;

    @NotNull(message = "Môn học bắt buộc có")
    @Min(value = 1, message = "ID môn học không âm")
    private Long subjectId;

    @NotNull(message = "Ngày thi bắt buộc nhập")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate testDate;

    @NotNull(message = "Điểm bắt buộc có")
    @Min(value = 1, message = "Điểm không âm")
    private Double score;
}
