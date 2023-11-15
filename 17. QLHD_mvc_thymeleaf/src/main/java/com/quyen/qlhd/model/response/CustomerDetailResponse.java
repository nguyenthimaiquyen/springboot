package com.quyen.qlhd.model.response;

import com.quyen.qlhd.statics.CustomerType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class CustomerDetailResponse {
    private int id;
    private String fullname;
    private String address;
    private String phone;
    private CustomerType customerType;

}
