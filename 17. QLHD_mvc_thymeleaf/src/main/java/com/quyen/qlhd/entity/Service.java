package com.quyen.qlhd.entity;

import com.quyen.qlhd.statics.Unit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Service {
    private int id;
    private String name;
    private double price;
    private Unit unit;
    private double period;

}
