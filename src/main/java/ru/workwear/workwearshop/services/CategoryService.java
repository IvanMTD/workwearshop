package ru.workwear.workwearshop.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.workwear.workwearshop.repositories.CategoryRepository;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
}
