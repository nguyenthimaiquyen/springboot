package com.quyen.test.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRequest {
    private Long id;

    @NotBlank(message = "Name is required")
    @Length(max = 255, message = "Name must be less than 255 characters")
    private String name;

    @NotNull(message = "Price is required")
    @Min(value = 1, message = "Price must be positive number")
    private Float price;

    @Length(max = 1000, message = "Description must be less than 1000 characters")
    private String description;

    @NotEmpty(message = "Image is required")
    private List<String> image;


}
