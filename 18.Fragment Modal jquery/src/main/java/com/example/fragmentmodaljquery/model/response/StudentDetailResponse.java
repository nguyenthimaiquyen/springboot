package com.example.fragmentmodaljquery.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentDetailResponse {
    private int id;
    private String name;
    private String address;
    private String phone;
    private String className;

}
