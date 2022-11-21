package ru.practicum.explorewithme.model.compilation;

import ru.practicum.explorewithme.model.event.EventShortDto;

import java.util.List;

public class CompilationDto {

    private List<EventShortDto> events;
    private Long id;
    private Boolean pinned;
    private String title;

}
