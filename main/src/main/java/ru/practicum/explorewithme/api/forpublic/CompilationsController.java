package ru.practicum.explorewithme.api.forpublic;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explorewithme.mapper.CompilationMapper;
import ru.practicum.explorewithme.model.compilation.Compilation;
import ru.practicum.explorewithme.model.compilation.CompilationDto;
import ru.practicum.explorewithme.service.publicservices.CompilationService;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/compilations")
public class CompilationsController {

    private final CompilationService compilationService;

    @GetMapping
    public List<CompilationDto> getCompilations(@RequestParam(required = false) Boolean pinned,
                                                @RequestParam(defaultValue = "0") Integer from,
                                                @RequestParam(defaultValue = "10") Integer size
    ) {

        List<Compilation> compilationsList = compilationService.getCompilations(pinned, from, size);
        return compilationsList.stream().map(CompilationMapper::toCompilationDto)
                .collect(Collectors.toList());

    }

    @GetMapping("/{compId}")
    public CompilationDto getCompilationId(@PathVariable Long compId) {
        return CompilationMapper.toCompilationDto(compilationService.getCompilationId(compId));
    }
}
