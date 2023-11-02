package ru.workwear.workwearshop.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.workwear.workwearshop.models.Category;

import java.util.List;

public interface CategoryRepository extends CrudRepository<Category,Long> {
    List<Category> findAll();
}
