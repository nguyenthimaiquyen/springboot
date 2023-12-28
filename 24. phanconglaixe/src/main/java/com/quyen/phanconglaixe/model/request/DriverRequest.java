package com.quyen.phanconglaixe.model.request;

import com.quyen.phanconglaixe.statics.Level;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DriverRequest {

    private Long id;

    @NotBlank(message = "Name is required")
    @Length(max = 255, message = "Name must be less than 255 characters")
    private String name;

    @NotBlank(message = "Address is required")
    @Length(max = 255, message = "Address must be less than 255 characters")
    private String address;

    @NotBlank(message = "Phone is required")
    @Length(max = 10, message = "Phone must be 10 characters, start with zero")
    private String phone;

    @NotNull(message = "Status is required")
    private Level level;


}
