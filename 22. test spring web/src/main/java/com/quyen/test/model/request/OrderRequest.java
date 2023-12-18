package com.quyen.test.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {

    private Long id;

    @NotBlank(message = "Name is required")
    @Length(max = 255, message = "Name must be less than 255 characters")
    private String name;

    @NotBlank(message = "Phone is required")
    @Length(max = 10, message = "Phone must be 10 characters, start with zero")
    private String phone;

    @NotBlank(message = "Email is required")
    @Email(regexp = "^[A-Za-z0-9+_.-]+@(.+)$",
            message = "Email must be email format")
    @Length(max = 150, message = "Email must be less than 150 characters")
    private String email;

    @NotBlank(message = "Created time is required")
    private LocalDateTime createdAt;

    @NotBlank(message = "Handled time is required")
    private LocalDateTime handledAt;
}
