package ru.workwear.workwearshop.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.workwear.workwearshop.repositories.SizeRepository;

@Service
@RequiredArgsConstructor
public class SizeService {
    private final SizeRepository sizeRepository;
}
