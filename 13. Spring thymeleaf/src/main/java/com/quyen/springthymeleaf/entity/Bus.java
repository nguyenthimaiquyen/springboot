package com.quyen.springthymeleaf.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Bus {
    private int id;
    private int distance;
    private int busStop;


}
