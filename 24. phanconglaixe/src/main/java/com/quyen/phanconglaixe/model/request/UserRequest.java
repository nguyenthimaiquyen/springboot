package com.quyen.phanconglaixe.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    @NotBlank(message = "Username is required")
    @Length(max = 100, message = "Username must be less than 100 characters")
    private String username;

    @NotBlank(message = "Password is required")
    @Length(max = 255, message = "Password must be less than 255 characters")
    private String password;

}
