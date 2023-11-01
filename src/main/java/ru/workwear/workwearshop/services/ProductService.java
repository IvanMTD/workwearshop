package ru.workwear.workwearshop.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.workwear.workwearshop.repositories.ProductRepository;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
}
