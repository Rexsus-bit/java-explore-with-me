package ru.practicum.explorewithme.api.forpublic;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explorewithme.model.category.CategoryDto;
import ru.practicum.explorewithme.service.publicservices.CategoryService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/categories")
public class CategoriesController {

    private final CategoryService categoryService;
    private final ModelMapper modelMapper;

    @GetMapping
    public List<CategoryDto> getCategories(@RequestParam(defaultValue = "0") Integer from,
                                           @RequestParam(defaultValue = "10") Integer size
    ) {
        return modelMapper.map(categoryService.getCategories(from, size)
                , new TypeToken<List<CategoryDto>>() {
                }.getType());
    }

    @GetMapping("/{catId}")
    public CategoryDto getCategoryById(@PathVariable Long catId) {
        return modelMapper.map(categoryService.getCategoryById(catId), CategoryDto.class);
    }
}
