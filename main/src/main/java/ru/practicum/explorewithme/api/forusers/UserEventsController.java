package ru.practicum.explorewithme.api.forusers;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explorewithme.model.event.EventFullDto;
import ru.practicum.explorewithme.model.event.NewEventDto;
import ru.practicum.explorewithme.model.event.UpdateEventRequest;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/users")
public class UserEventsController {

    @GetMapping("/{userId}/events")
    public ResponseEntity<Object> getEventsOfUser(@PathVariable Long userId,
                                                  @RequestParam(defaultValue = "0") Integer from,
                                                  @RequestParam(defaultValue = "10") Integer size) {
        return null;
    }

    @PatchMapping("/{userId}/events")
    public ResponseEntity<Object> updateEventOfUser(@PathVariable Long userId,
                                                    @RequestBody UpdateEventRequest updateEventRequest) {
        return null;
    }

    @PostMapping("/{userId}/events")
    public ResponseEntity<EventFullDto> createEvent(@PathVariable Long userId,
                                                    @Valid @RequestBody NewEventDto newEventDto) {
        return null;
    }



}
