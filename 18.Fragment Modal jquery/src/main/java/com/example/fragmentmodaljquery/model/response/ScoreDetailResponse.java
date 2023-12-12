package com.example.fragmentmodaljquery.model.response;

import com.example.fragmentmodaljquery.entity.Student;
import com.example.fragmentmodaljquery.entity.Subject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScoreDetailResponse {
    private Long id;
    private Student student;
    private Subject subject;
    private LocalDate testDate;
    private double score;
}
