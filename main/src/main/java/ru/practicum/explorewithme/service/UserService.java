package ru.practicum.explorewithme.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.practicum.explorewithme.model.event.Event;
import ru.practicum.explorewithme.model.event.EventFullDto;
import ru.practicum.explorewithme.model.event.NewEventDto;
import ru.practicum.explorewithme.repository.EventJpaRepository;
import ru.practicum.explorewithme.repository.UserJpaRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final ModelMapper modelMapper;
    private final UserJpaRepository userJpaRepository;
    private final EventJpaRepository eventJpaRepository;


    public Event createEvent(Long userId, NewEventDto newEventDto) {
        Event event = modelMapper.map(newEventDto, Event.class);
        event.setInitiator(userJpaRepository.findById(userId).orElseThrow(RuntimeException::new)); // TODO ошибка
        return   eventJpaRepository.save(event);
    }
}
