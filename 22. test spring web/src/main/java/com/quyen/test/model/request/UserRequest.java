package com.quyen.test.model.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UserRequest {

    @NotBlank
    @Size(max = 100)
    private String username;

    @NotBlank
    private String password;

}
