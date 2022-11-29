package ru.practicum.explorewithme.service.publicservices;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.explorewithme.exceptions.ObjectDoesNotExistException;
import ru.practicum.explorewithme.model.category.Category;
import ru.practicum.explorewithme.repository.CategoryJpaRepository;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryJpaRepository categoryJpaRepository;


    public Category getCategoryById(Long catId) {
       return categoryJpaRepository.findById(catId).orElseThrow(ObjectDoesNotExistException::new); // TODO настроить исключения
    }
}
