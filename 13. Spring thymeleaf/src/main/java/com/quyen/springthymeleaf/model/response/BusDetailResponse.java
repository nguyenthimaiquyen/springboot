package com.quyen.springthymeleaf.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
@AllArgsConstructor
public class BusDetailResponse {
    private int id;
    private int distance;
    private int busStop;


}
