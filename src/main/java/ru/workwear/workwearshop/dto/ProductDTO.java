package ru.workwear.workwearshop.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class ProductDTO {
    private String name;
    private String description;
    private BigDecimal coast;
    private Date placedAt;
    private MultipartFile file;
    private String image;
    private CategoryDTO category;
    private List<SizeDTO> sizes;
}
