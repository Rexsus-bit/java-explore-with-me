package ru.practicum.explorewithme.api.foradmin;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explorewithme.model.category.Category;
import ru.practicum.explorewithme.model.category.CategoryDto;
import ru.practicum.explorewithme.model.category.NewCategoryDto;
import ru.practicum.explorewithme.model.event.AdminUpdateEventRequest;
import ru.practicum.explorewithme.model.event.EventFullDto;
import ru.practicum.explorewithme.model.event.State;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/admin")
public class AdminController {


    @GetMapping("/events")
    public ResponseEntity<List<EventFullDto>> findEvents(@RequestParam List<Long> users,
                                                         @RequestParam List<State> states,
                                                         @RequestParam List<Long> categories,
                                                         @RequestParam LocalDateTime rangeStart,
                                                         @RequestParam LocalDateTime rangeEnd,
                                                         @RequestParam(defaultValue = "0") Integer from,
                                                         @RequestParam(defaultValue = "10") Integer size
    ) {
        return null;
    }

    @PutMapping("/events/{eventId}")
    public ResponseEntity<EventFullDto> editEvent(@PathVariable Long eventId,
                                                  @RequestBody AdminUpdateEventRequest adminUpdateEventRequest) {

        return null;
    }

    @PatchMapping("/events/{eventId}/publish")
    public ResponseEntity<EventFullDto> publishEvent(@PathVariable Long eventId) {
        return null;
    }

    @PatchMapping("/events/{eventId}/reject")
    public ResponseEntity<EventFullDto> rejectEvent(@PathVariable Long eventId) {
        return null;
    }

    @PatchMapping("/categories")
    public ResponseEntity<CategoryDto> changeCategory(@RequestBody CategoryDto categoryDto) {
        return null;
    }

    @PostMapping("/categories")
    public ResponseEntity<CategoryDto> addNewCategory(@RequestBody NewCategoryDto newCategoryDto) {
        return null;
    }

    @DeleteMapping("/categories")
    public ResponseEntity<Void> deleteCategory(@RequestBody NewCategoryDto newCategoryDto) { // TODO Подумать над конструкцией
       return null;
    }

    @GetMapping("/users")
    public ResponseEntity<List<EventFullDto>> findUsers(@RequestParam List<Long> users,
                                                         @RequestParam List<State> states,
                                                         @RequestParam List<Long> categories,
                                                         @RequestParam LocalDateTime rangeStart,
                                                         @RequestParam LocalDateTime rangeEnd,
                                                         @RequestParam(defaultValue = "0") Integer from,
                                                         @RequestParam(defaultValue = "10") Integer size
    ) {
        return null;
    }

}
