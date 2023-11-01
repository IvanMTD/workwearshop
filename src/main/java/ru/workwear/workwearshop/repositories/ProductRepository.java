package ru.workwear.workwearshop.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.workwear.workwearshop.models.Product;

public interface ProductRepository extends CrudRepository<Product,Long> {

}
