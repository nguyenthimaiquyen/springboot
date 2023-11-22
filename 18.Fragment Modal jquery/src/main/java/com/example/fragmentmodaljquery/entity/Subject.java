package com.example.fragmentmodaljquery.entity;

import com.example.fragmentmodaljquery.statics.SubjectType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Subject {
    private int id;
    private String subjectName;
    private int credit;
    private SubjectType subjectType;

}
