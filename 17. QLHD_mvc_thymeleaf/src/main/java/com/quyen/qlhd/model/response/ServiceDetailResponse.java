package com.quyen.qlhd.model.response;

import com.quyen.qlhd.statics.Unit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ServiceDetailResponse {
    private int id;
    private String name;
    private double price;
    private Unit unit;
    private double period;

}
