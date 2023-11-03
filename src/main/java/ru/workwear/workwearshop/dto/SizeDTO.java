package ru.workwear.workwearshop.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SizeDTO {
    private long id;
    private String name;
    private String description;
    private int number;
    private ProductDTO product;
}
