package ru.practicum.explorewithme.api.forpublic;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/categories")
public class CategoriesController {

    @GetMapping
    public ResponseEntity<Object> getCategories(@RequestParam(defaultValue = "0") Integer from,
                                                @RequestParam(defaultValue = "10") Integer size
    ) {
        return null;
    }

    @GetMapping("/{catId}")
    public ResponseEntity<Object> getCategoriesById(@PathVariable Long catId) {
        return null;
    }

}
