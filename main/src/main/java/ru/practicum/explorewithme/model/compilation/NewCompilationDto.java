package ru.practicum.explorewithme.model.compilation;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class NewCompilationDto {

    private List<Long> events;
    private Boolean pinned; //TODO by default false? Как настроить?
    private String title;

}
