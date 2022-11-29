package ru.practicum.explorewithme.api.forpublic;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explorewithme.model.category.CategoryDto;
import ru.practicum.explorewithme.service.publicservices.CategoryService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/categories")
public class CategoriesController {

    private final CategoryService categoryService;
    private final ModelMapper modelMapper;



    @GetMapping
    public ResponseEntity<Object> getCategories(@RequestParam(defaultValue = "0") Integer from,
                                                @RequestParam(defaultValue = "10") Integer size
    ) {
        return null;
    }

    @GetMapping("/{catId}")
    public CategoryDto getCategoryById(@PathVariable Long catId) {
        return modelMapper.map(categoryService.getCategoryById(catId), CategoryDto.class);
    }

}
