package com.quyen.quanlyfile.model.request;

import lombok.Data;

@Data
public class SearchImageRequest {
    private String type;
    private int currentPage = 0;
    private int pageSize = 5;
}
