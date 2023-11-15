package com.quyen.qlhd.model.request;

import com.quyen.qlhd.statics.Unit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ServiceCreationRequest {
    private String name;
    private double price;
    private Unit unit;
    private double period;

}
