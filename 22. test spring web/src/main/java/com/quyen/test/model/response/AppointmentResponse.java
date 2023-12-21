package com.quyen.test.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppointmentResponse {
    private List<AppointmentDetailResponse> appointments;
    private Long totalElement;
    private int totalPage;
    private int currentPage;
    private int pageSize;

}
