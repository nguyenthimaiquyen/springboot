package com.quyen.springthymeleaf.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Assignment {
    private int id;
    private Bus bus;
    private Driver driver;
    private int driving;
    private int assignmentTime;


}
