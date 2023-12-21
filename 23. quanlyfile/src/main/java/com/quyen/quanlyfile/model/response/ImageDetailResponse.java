package com.quyen.quanlyfile.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageDetailResponse {
    private Integer id;
    private String type;
    private LocalDate createdAt;
    private Long totalRecord;


}
