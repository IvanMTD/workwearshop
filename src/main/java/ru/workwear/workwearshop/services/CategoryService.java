package ru.workwear.workwearshop.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.workwear.workwearshop.dto.CategoryDTO;
import ru.workwear.workwearshop.models.Category;
import ru.workwear.workwearshop.repositories.CategoryRepository;
import ru.workwear.workwearshop.repositories.ProductRepository;
import ru.workwear.workwearshop.utils.ImageEncryptUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public Category save(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());
        category.setInternal(categoryDTO.isInternal());
        if(!categoryDTO.getFile().isEmpty()){
            try {
                category.setImage(categoryDTO.getFile().getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else{
            byte[] image = ImageEncryptUtil.getImageBlob("./src/main/resources/static/images/logo.png");
            System.out.println(image.length);
            category.setImage(image);
        }
        category.setProducts(new ArrayList<>());
        return categoryRepository.save(category);
    }

    public List<CategoryDTO> findAllLazy(boolean authFactor) {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDTO> categoriesDTO = new ArrayList<>();
        for(Category category : categories){
            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setId(category.getId());
            categoryDTO.setName(category.getName());
            categoryDTO.setDescription(category.getDescription());
            categoryDTO.setInternal(category.isInternal());
            categoryDTO.setImage(ImageEncryptUtil.getImgData(category.getImage()));
            categoryDTO.setProducts(new ArrayList<>());
            if(authFactor){
                categoriesDTO.add(categoryDTO);
            }else{
                if(!categoryDTO.isInternal()){
                    categoriesDTO.add(categoryDTO);
                }
            }
        }
        return categoriesDTO;
    }
}
