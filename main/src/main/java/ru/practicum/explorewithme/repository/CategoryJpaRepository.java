package ru.practicum.explorewithme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.practicum.explorewithme.model.category.Category;

@Repository
public interface CategoryJpaRepository extends JpaRepository<Category, Long> {

    Category findByName(String name);

}