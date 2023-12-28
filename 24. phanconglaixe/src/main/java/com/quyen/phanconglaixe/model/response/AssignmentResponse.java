package com.quyen.phanconglaixe.model.response;

import com.quyen.phanconglaixe.entity.Bus;
import com.quyen.phanconglaixe.entity.Driver;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AssignmentResponse {

    private Long id;

    private Integer driving;

    private Integer assignmentTime;

    private Bus bus;

    private Driver driver;
}
