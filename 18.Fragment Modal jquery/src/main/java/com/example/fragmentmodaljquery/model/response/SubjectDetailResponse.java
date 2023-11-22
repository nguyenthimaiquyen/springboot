package com.example.fragmentmodaljquery.model.response;

import com.example.fragmentmodaljquery.statics.SubjectType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubjectDetailResponse {
    private int id;
    private String subjectName;
    private int credit;
    private SubjectType subjectType;

}
