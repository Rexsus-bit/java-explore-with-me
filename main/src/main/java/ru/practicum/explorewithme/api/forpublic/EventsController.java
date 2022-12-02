package ru.practicum.explorewithme.api.forpublic;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explorewithme.mapper.EventMapper;
import ru.practicum.explorewithme.model.event.EventFullDto;
import ru.practicum.explorewithme.model.event.EventShortDto;
import ru.practicum.explorewithme.service.publicservices.EventService;

import java.time.LocalDateTime;
import java.util.List;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/events")
public class EventsController {

    private final EventService eventService;


    @GetMapping
    public List<EventShortDto> getEvents(@RequestParam (required = false) String text,
                                         @RequestParam (required = false) List<Integer> categories,
                                         @RequestParam (required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
                                             LocalDateTime rangeStart,
                                         @RequestParam (required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
                                             LocalDateTime rangeEnd,
                                         @RequestParam (defaultValue = "false") Boolean onlyAvailable,
                                         @RequestParam (required = false) String sort,
                                         @RequestParam (defaultValue = "0") Integer from,
                                         @RequestParam (defaultValue = "10") Integer size
                                            ){
        return eventService.getEvents(text, categories, rangeStart, rangeEnd, onlyAvailable, sort, from, size);
    } // TODO НЕ СДЕЛАН

    @GetMapping("/{id}")
    public EventFullDto getEventById(@PathVariable Long id) {
        return EventMapper.toEventFullDto(eventService.getEventById(id));
    }
}