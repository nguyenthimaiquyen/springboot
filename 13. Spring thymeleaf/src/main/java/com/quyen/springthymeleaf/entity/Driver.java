package com.quyen.springthymeleaf.entity;

import com.quyen.springthymeleaf.statics.Level;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Driver {
    private int id;
    private String name;
    private String address;
    private String phone;
    private Level level;

}
