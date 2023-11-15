package com.quyen.qlhd.entity;

import com.quyen.qlhd.statics.CustomerType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {
    private int id;
    private String fullname;
    private String address;
    private String phone;
    private CustomerType customerType;

}
