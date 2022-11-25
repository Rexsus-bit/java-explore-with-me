package ru.practicum.explorewithme.service.publicservices;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.practicum.explorewithme.exceptions.CategoryNotFoundException;
import ru.practicum.explorewithme.model.category.Category;
import ru.practicum.explorewithme.repository.CategoryJpaRepository;
import ru.practicum.explorewithme.util.OffsetLimitPageable;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryJpaRepository categoryJpaRepository;

    public Category getCategoryById(Long catId) {
       return categoryJpaRepository.findById(catId).orElseThrow( CategoryNotFoundException::new); // TODO настроить исключения
    }

    public List<Category> getCategories(Integer from, Integer size) {
        Pageable page = OffsetLimitPageable.of(from, size);
        return categoryJpaRepository.findAll(page).getContent();
    }
}
