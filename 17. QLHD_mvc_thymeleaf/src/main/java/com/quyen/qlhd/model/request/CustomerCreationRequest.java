package com.quyen.qlhd.model.request;

import com.quyen.qlhd.statics.CustomerType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerCreationRequest {
    private String fullname;
    private String address;
    private String phone;
    private CustomerType customerType;
}
