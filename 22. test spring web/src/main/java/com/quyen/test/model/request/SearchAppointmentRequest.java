package com.quyen.test.model.request;

import lombok.Data;

@Data
public class SearchAppointmentRequest {
    private String name;
    private int currentPage = 0;
    private int pageSize = 2;
}
