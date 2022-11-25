package ru.practicum.explorewithme.service.publicservices;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.explorewithme.exceptions.EventNotFoundException;
import ru.practicum.explorewithme.mapper.EventMapper;
import ru.practicum.explorewithme.model.event.Event;
import ru.practicum.explorewithme.model.event.EventShortDto;
import ru.practicum.explorewithme.model.event.EventSortType;
import ru.practicum.explorewithme.repository.EventCriteriaRepository;
import ru.practicum.explorewithme.repository.EventJpaRepository;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventJpaRepository eventJpaRepository;
    private final EventCriteriaRepository eventCriteriaRepository;

    public List<Event> getEvents(String text, List<Long> categories, LocalDateTime rangeStart
            , LocalDateTime rangeEnd, Boolean onlyAvailable, EventSortType sort, Integer from, Integer size) {
        List<Event> events = eventCriteriaRepository.findEventsByCustomCriteria(null, null, categories
                , rangeStart, rangeEnd, from, size, text);

        if (sort.equals(EventSortType.VIEWS)){
            events = events.stream().sorted(Comparator.comparing(Event::getViews))
                    .collect(Collectors.toList());
        }
        if (sort.equals(EventSortType.EVENT_DATE)){
            events = events.stream().sorted(Comparator.comparing(Event::getEventDate))
                    .collect(Collectors.toList());
        }
        if (onlyAvailable) {
           return events.stream().filter(a -> {
                if (a.getParticipantLimit() == null || a.getParticipantLimit() == 0) return false;
                return a.getConfirmedRequests() < a.getParticipantLimit();
            }).collect(Collectors.toList());
        } else return events;


    }

    public Event getEventById(Long id) {
       return eventJpaRepository.findById(id).orElseThrow(EventNotFoundException::new);
    }
}
