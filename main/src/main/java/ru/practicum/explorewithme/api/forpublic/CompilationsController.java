package ru.practicum.explorewithme.api.forpublic;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/compilations")
public class CompilationsController {


    @GetMapping
    public ResponseEntity<Object> getCompilations(@RequestParam(required = false) Boolean pinned,
                                            @RequestParam (defaultValue = "0") Integer from,
                                            @RequestParam (defaultValue = "10") Integer size
    ){
        return null;
    }

    @GetMapping("/{compId}")
    public ResponseEntity<Object> getCompilationId(@PathVariable Long compId) {
        return null;
    }

}
