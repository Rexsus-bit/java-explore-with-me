package ru.practicum.explorewithme.model.compilation;

import lombok.*;
import ru.practicum.explorewithme.model.event.EventShortDto;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompilationDto {

    private List<EventShortDto> events;
    private Long id;
    private Boolean pinned;
    private String title;

}
