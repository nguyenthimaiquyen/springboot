package com.quyen.quanlyfile.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageResponse {
    private List<ImageDetailResponse> images;
    private Long totalElement;
    private int totalPage;
    private int currentPage;
    private int pageSize;

}
