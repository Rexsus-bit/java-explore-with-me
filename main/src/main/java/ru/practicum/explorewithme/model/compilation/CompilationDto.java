package ru.practicum.explorewithme.model.compilation;

import lombok.Getter;
import lombok.Setter;
import ru.practicum.explorewithme.model.event.EventShortDto;

import java.util.List;

@Getter
@Setter
public class CompilationDto {

    private List<EventShortDto> events;
    private Long id;
    private Boolean pinned;
    private String title;

}
