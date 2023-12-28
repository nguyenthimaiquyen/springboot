package com.quyen.phanconglaixe.model.request;

import com.quyen.phanconglaixe.statics.Level;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BusRequest {

    private Long id;

    @NotNull(message = "Distance is required")
    @Min(value = 1, message = "Distance must be greater than 1")
    private Integer distance;

    @NotNull(message = "Bus stop is required")
    @Min(value = 1, message = "Bus stop must be greater than 1")
    private Integer busStop;



}
