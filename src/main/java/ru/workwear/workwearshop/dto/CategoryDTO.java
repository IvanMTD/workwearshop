package ru.workwear.workwearshop.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@NoArgsConstructor
public class CategoryDTO {
    private String name;
    private String description;
    private MultipartFile file;
    private String image;
    private List<ProductDTO> products;
}
