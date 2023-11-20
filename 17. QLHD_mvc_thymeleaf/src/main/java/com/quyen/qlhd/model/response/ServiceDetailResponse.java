package com.quyen.qlhd.model.response;

import com.quyen.qlhd.statics.Unit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServiceDetailResponse {
    private Integer id;
    private String name;
    private Double price;
    private Unit unit;
    private Double period;

}
