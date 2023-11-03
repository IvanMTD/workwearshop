package ru.workwear.workwearshop.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@NoArgsConstructor
public class CategoryDTO {
    private long id;
    @Size(min = 4, max = 16, message = "The length of the user name must be from 4 to 16 characters")
    @NotBlank(message = "The field cannot be empty")
    private String name;
    @NotBlank(message = "The field cannot be empty")
    private String description;
    private MultipartFile file;
    private boolean internal;
    private String image;
    private List<ProductDTO> products;
}
