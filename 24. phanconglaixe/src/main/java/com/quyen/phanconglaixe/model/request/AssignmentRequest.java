package com.quyen.phanconglaixe.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AssignmentRequest {

    private Long id;

    @NotNull(message = "Driving is required")
    @Min(value = 1, message = "Driving must be greater than 1")
    private Integer driving;

    @NotNull(message = "Assignment time is required")
    @Min(value = 1, message = "Assignment time (month) must be greater than 1")
    private Integer assignmentTime;

    @NotNull(message = "Driver is required")
    @Min(value = 1, message = "Driver's ID must be greater than 1")
    private Long driverId;

    @NotNull(message = "Bus stop is required")
    @Min(value = 1, message = "Bus's ID must be greater than 1")
    private Long busId;

}
