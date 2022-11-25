package ru.practicum.explorewithme.api.foradmin;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explorewithme.Mapper;
import ru.practicum.explorewithme.model.category.CategoryDto;
import ru.practicum.explorewithme.model.category.NewCategoryDto;
import ru.practicum.explorewithme.model.event.AdminUpdateEventRequest;
import ru.practicum.explorewithme.model.event.Event;
import ru.practicum.explorewithme.model.event.EventFullDto;
import ru.practicum.explorewithme.model.event.State;
import ru.practicum.explorewithme.model.user.NewUserRequest;
import ru.practicum.explorewithme.model.user.User;
import ru.practicum.explorewithme.model.user.UserDto;
import ru.practicum.explorewithme.service.AdminService;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/admin")
public class AdminController {

    private final AdminService adminService;
//    private final Mapper mapper;
    private final ModelMapper modelMapper;

    @GetMapping("/events")
    public ResponseEntity<List<EventFullDto>> findEvents(@RequestParam List<Long> users,
                                                         @RequestParam List<State> states,
                                                         @RequestParam List<Long> categories,
                                                         @RequestParam LocalDateTime rangeStart,
                                                         @RequestParam LocalDateTime rangeEnd,
                                                         @RequestParam(defaultValue = "0") Integer from,
                                                         @RequestParam(defaultValue = "10") Integer size
    ) {
//        List<Event> eventsList = adminService.findEvents(users, states, categories, rangeStart, rangeEnd, from, size);
//        return mapper.toEventsFullDtoList(eventsList);
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
    public ResponseEntity<Void> deleteUsers(@PathVariable Integer userId) { // TODO подумать над возвращаемм типом
        return null;
    }

    @PostMapping("/compilations")
    public ResponseEntity<UserDto> addCompilation(@RequestBody NewUserRequest newUserRequest) {
        return null;
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