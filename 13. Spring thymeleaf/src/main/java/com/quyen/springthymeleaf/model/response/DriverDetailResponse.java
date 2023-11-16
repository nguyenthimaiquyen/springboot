package com.quyen.springthymeleaf.model.response;

import com.quyen.springthymeleaf.statics.Level;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
@AllArgsConstructor
public class DriverDetailResponse {
    private int id;
    private String name;
    private String address;
    private String phone;
    private Level level;


}
