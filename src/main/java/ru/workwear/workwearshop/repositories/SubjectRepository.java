package ru.workwear.workwearshop.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.workwear.workwearshop.models.Subject;

import java.util.Optional;

public interface SubjectRepository extends CrudRepository<Subject,Long> {
    Optional<Subject> findByUsername(String username);
}
