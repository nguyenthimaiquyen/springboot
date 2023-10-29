package com.quyen.springmvc02.model.request;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class ReaderCreationRequest {
    private String fullname;
    private String address;
    private String phone;



}
