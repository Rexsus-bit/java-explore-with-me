package ru.practicum.explorewithme.api.foradmin;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explorewithme.exceptions.ObjectDoesNotExistException;
import ru.practicum.explorewithme.mapper.CompilationMapper;
import ru.practicum.explorewithme.model.category.Category;
import ru.practicum.explorewithme.model.category.CategoryDto;
import ru.practicum.explorewithme.model.category.NewCategoryDto;
import ru.practicum.explorewithme.model.compilation.Compilation;
import ru.practicum.explorewithme.model.compilation.CompilationDto;
import ru.practicum.explorewithme.model.compilation.NewCompilationDto;
import ru.practicum.explorewithme.model.event.AdminUpdateEventRequest;
import ru.practicum.explorewithme.model.event.Event;
import ru.practicum.explorewithme.model.event.EventFullDto;
import ru.practicum.explorewithme.model.event.State;
import ru.practicum.explorewithme.model.user.NewUserRequest;
import ru.practicum.explorewithme.model.user.User;
import ru.practicum.explorewithme.model.user.UserDto;
import ru.practicum.explorewithme.model.user.UserShortDto;
import ru.practicum.explorewithme.service.AdminService;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/admin")
public class AdminController {

    private final AdminService adminService;
    private final ModelMapper modelMapper;
    private final CompilationMapper compilationMapper;

    @GetMapping("/events")
    public ResponseEntity<List<EventFullDto>> findEvents(@RequestParam List<Long> users,
                                                         @RequestParam List<State> states,
                                                         @RequestParam List<Long> categories,
                                                         @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime rangeStart, // TODO что будет если убрать формат?
                                                         @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime rangeEnd,
                                                         @RequestParam(defaultValue = "0") Integer from,
                                                         @RequestParam(defaultValue = "10") Integer size
    ) {
        List<Event> eventsList = adminService.findEvents(users, states, categories, rangeStart, rangeEnd, from, size);
        return modelMapper.map(eventsList, new TypeToken<List<EventFullDto>>() { // TODO допилить маппинг в части полей
        }.getType());
    }

    @PutMapping("/events/{eventId}")
    public EventFullDto editEvent(@PathVariable Long eventId,
                                  @RequestBody AdminUpdateEventRequest adminUpdateEventRequest) {
        Event event = adminService.editEvent(eventId, adminUpdateEventRequest);
        EventFullDto eventFullDto = modelMapper.map(event, EventFullDto.class);
        eventFullDto.setCategoryDto(modelMapper.map(event.getCategory(), CategoryDto.class));
        eventFullDto.setInitiator(modelMapper.map(event.getInitiator(), UserShortDto.class));
        return eventFullDto;
    }

    @PatchMapping("/events/{eventId}/publish")
    public EventFullDto publishEvent(@PathVariable Long eventId) {
        return modelMapper.map(adminService.publishEvent(eventId), EventFullDto.class);
    }

    @PatchMapping("/events/{eventId}/reject")
    public EventFullDto rejectEvent(@PathVariable Long eventId) {
        return modelMapper.map(adminService.rejectEvent(eventId), EventFullDto.class);
    }

    @PatchMapping("/categories")
    public CategoryDto changeCategory(@RequestBody CategoryDto categoryDto) {
        Category category = adminService.changeCategory(modelMapper.map(categoryDto, Category.class));
        return modelMapper.map(category, CategoryDto.class);
    }

    @PostMapping("/categories")
    public CategoryDto addNewCategory(@RequestBody NewCategoryDto newCategoryDto) {
        Category category = adminService.addNewCategory(modelMapper.map(newCategoryDto, Category.class));
        return modelMapper.map(category, CategoryDto.class);
    }

    @DeleteMapping("/categories/{catId}")
    public void deleteCategory(@PathVariable Long catId) {
            adminService.deleteCategory(catId);
    }

    @GetMapping("/users")
    public List<UserDto> findUsers(@RequestParam(required = false) List<Long> ids,
                                   @RequestParam(defaultValue = "0") Integer from,
                                   @RequestParam(defaultValue = "3") Integer size
    ) {
        List<User> users = adminService.findUsers(ids, from, size);
        return modelMapper.map(users, new TypeToken<List<UserDto>>() {
        }.getType());
    }

    @PostMapping("/users")
    public UserDto addUser(@RequestBody NewUserRequest newUserRequest) {
        User user = adminService.addUser(newUserRequest);
        return modelMapper.map(user, UserDto.class);
    }

    @DeleteMapping("/users/{userId}")
    public void deleteUsers(@PathVariable Long userId) {
        adminService.deleteUsers(userId);
    }

    @PostMapping("/compilations")
    public CompilationDto addCompilation(@RequestBody NewCompilationDto newCompilationDto) {
        Compilation compilation = adminService.addCompilation(newCompilationDto);
        return compilationMapper.toCompilationDto(compilation);
    }

    @DeleteMapping("/compilations/{compId}")
    public ResponseEntity<Void> deleteCompilation(@PathVariable String compId) {// TODO подумать над возвращаемм типом
        return null;
    }

    @DeleteMapping("/compilations/{compId}/events/{eventId}")
    public ResponseEntity<Void> deleteEventFromCompilation(@PathVariable Integer compId, @PathVariable Integer eventId) { // TODO подумать над возвращаемм типом
        return null;
    }

    @PatchMapping("/compilations/{compId}/events/{eventId}")
    public ResponseEntity<Void> addLinkEventToCompilation(@PathVariable Integer compId, @PathVariable Integer eventId) { //
        return null;
    }

    @DeleteMapping("/compilations/{compId}/pin")
    public ResponseEntity<Void> unpinCompilation(@PathVariable Integer compId) {
        return null;
    }

    @PatchMapping("/compilations/{compId}/pin")
    public ResponseEntity<Void> pinCompilation(@PathVariable Integer compId) {
        return null;
    }

}
