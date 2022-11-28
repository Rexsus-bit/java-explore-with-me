package ru.practicum.explorewithme.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import ru.practicum.explorewithme.model.compilation.Compilation;
import ru.practicum.explorewithme.model.compilation.CompilationDto;
import ru.practicum.explorewithme.model.compilation.NewCompilationDto;
import ru.practicum.explorewithme.model.event.EventFullDto;
import ru.practicum.explorewithme.model.event.EventShortDto;

import java.util.List;

@RequiredArgsConstructor
public class CompilationMapper {

    private final ModelMapper modelMapper;


    public CompilationDto toCompilationDto(Compilation compilation) {
        List<EventShortDto> eventsShortDto = modelMapper.map(compilation.getEvents()
                , new TypeToken<List<EventShortDto>>() {
                }.getType());
        CompilationDto compilationDto = modelMapper.map(compilation, CompilationDto.class);
        compilationDto.setEvents(eventsShortDto);
        return compilationDto;
    }
}
