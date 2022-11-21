package ru.practicum.explorewithme.api;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/admin")
public class AdminController {

    @GetMapping("/users")
    public ResponseEntity<Object> get(@RequestParam long userId) {
            return null;
    }
}







