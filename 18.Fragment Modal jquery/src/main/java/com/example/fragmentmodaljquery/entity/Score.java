package com.example.fragmentmodaljquery.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Score {
    private int id;
    private Student student;
    private Subject subject;
    private LocalDate testDate;
    private double score;
}
