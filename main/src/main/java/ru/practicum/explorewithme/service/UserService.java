package ru.practicum.explorewithme.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.practicum.explorewithme.model.event.Event;
import ru.practicum.explorewithme.model.event.EventFullDto;
import ru.practicum.explorewithme.model.event.NewEventDto;
import ru.practicum.explorewithme.model.event.State;
import ru.practicum.explorewithme.repository.CategoryJpaRepository;
import ru.practicum.explorewithme.repository.EventJpaRepository;
import ru.practicum.explorewithme.repository.LocationJpaRepository;
import ru.practicum.explorewithme.repository.UserJpaRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {

    private final ModelMapper modelMapper;
    private final UserJpaRepository userJpaRepository;
    private final EventJpaRepository eventJpaRepository;
    private final CategoryJpaRepository categoryJpaRepository;
    private final LocationJpaRepository locationJpaRepository;

    public Event createEvent(Long userId, NewEventDto newEventDto) {
        Event event = modelMapper.map(newEventDto, Event.class);

        event.setInitiator(userJpaRepository.findById(userId).orElseThrow(RuntimeException::new)); //TODO
        event.setCategory(categoryJpaRepository.findById(newEventDto.getCategory()).orElseThrow(RuntimeException::new));// TODO ошибка
        event.setViews(0L);
        event.setConfirmedRequests(0L);
        event.setState(State.PENDING);
        event.setCreatedOn(LocalDateTime.now());
//        locationJpaRepository.save(event.getLocation());

        return eventJpaRepository.save(event);
    }
}
