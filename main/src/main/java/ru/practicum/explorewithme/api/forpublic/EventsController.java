package ru.practicum.explorewithme.api.forpublic;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/events")
public class EventsController {

    @GetMapping
    public ResponseEntity<Object> getEvents(@RequestParam (required = false) String text,
                                            @RequestParam (required = false) List<Integer> categories,
                                            @RequestParam (required = false) String rangeStart,
                                            @RequestParam (required = false) String rangeEnd,
                                            @RequestParam (defaultValue = "false") Boolean onlyAvailable,
                                            @RequestParam (required = false) String sort,
                                            @RequestParam (defaultValue = "0") Integer from,
                                            @RequestParam (defaultValue = "10") Integer size
                                            ){
        return new ResponseEntity<>("s", HttpStatus.valueOf(201));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getEventById(@PathVariable Long id) {
        return null;
    }
}