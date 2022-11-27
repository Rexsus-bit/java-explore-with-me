package ru.practicum.explorewithme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.practicum.explorewithme.model.category.Category;
import ru.practicum.explorewithme.model.event.Event;

@Repository
public interface CategoryJpaRepository extends JpaRepository<Category, Long> {


}