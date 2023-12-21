package com.quyen.quanlyfile.model.request;

import lombok.Data;

@Data
public class SearchUserRequest {
    private String name;
    private int currentPage = 0;
    private int pageSize = 5;
}
