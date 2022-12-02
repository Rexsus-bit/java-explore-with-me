package ru.practicum.explorewithme.service.publicservices;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.practicum.explorewithme.exceptions.EventNotFoundException;
import ru.practicum.explorewithme.model.event.Event;
import ru.practicum.explorewithme.model.event.EventFullDto;
import ru.practicum.explorewithme.model.event.EventShortDto;
import ru.practicum.explorewithme.repository.EventJpaRepository;
import ru.practicum.explorewithme.util.OffsetLimitPageable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventJpaRepository eventJpaRepository;
    public List<EventShortDto> getEvents(String text, List<Integer> categories, LocalDateTime rangeStart, LocalDateTime rangeEnd
            , Boolean onlyAvailable, String sort, Integer from, Integer size) {
        return null;


    }

    public Event getEventById(Long id) {
       return eventJpaRepository.findById(id).orElseThrow(EventNotFoundException::new);
    }
}
