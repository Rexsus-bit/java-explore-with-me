package ru.practicum.explorewithme.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.practicum.explorewithme.exceptions.CategoryNotFoundException;
import ru.practicum.explorewithme.exceptions.EventNotFoundException;
import ru.practicum.explorewithme.exceptions.UserNotFoundException;
import ru.practicum.explorewithme.model.Status;
import ru.practicum.explorewithme.model.event.*;
import ru.practicum.explorewithme.model.participationrequest.ParticipationRequest;
import ru.practicum.explorewithme.model.user.User;
import ru.practicum.explorewithme.repository.*;
import ru.practicum.explorewithme.util.OffsetLimitPageable;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final ModelMapper modelMapper;
    private final UserJpaRepository userJpaRepository;
    private final EventJpaRepository eventJpaRepository;
    private final CategoryJpaRepository categoryJpaRepository;
    private final LocationJpaRepository locationJpaRepository;
    private final ParticipationRequestJpaRepository participationRequestJpaRepository;

    public Event createEvent(Long userId, NewEventDto newEventDto) {
        Event event = modelMapper.map(newEventDto, Event.class);

        event.setInitiator(userJpaRepository.findById(userId).orElseThrow(RuntimeException::new)); //TODO
        event.setCategory(categoryJpaRepository.findById(newEventDto.getCategory()).orElseThrow(RuntimeException::new));// TODO ошибка
        event.setViews(0L);
        event.setConfirmedRequests(0L);
        event.setState(State.PENDING);
        event.setCreatedOn(LocalDateTime.now());
        return eventJpaRepository.save(event);
    }

    public Event updateEventOfCurrentUser(Long userId, UpdateEventRequest updateEventRequest) {
        Event event = eventJpaRepository.findById(updateEventRequest.getEventId())
                .orElseThrow(EventNotFoundException::new);
        updateEvent(event, updateEventRequest);
        return eventJpaRepository.save(event);
    }

    private void updateEvent(Event event, UpdateEventRequest updateEventRequest){
        if (updateEventRequest.getAnnotation() != null) event.setAnnotation(updateEventRequest.getAnnotation());
        if (updateEventRequest.getCategory() != null) event.setCategory(categoryJpaRepository
                .findById(updateEventRequest.getCategory())
                .orElseThrow(CategoryNotFoundException::new));
        if (updateEventRequest.getDescription() != null) event.setDescription(updateEventRequest.getDescription());
        if (updateEventRequest.getEventDate() != null) event.setEventDate(updateEventRequest.getEventDate());
        if (updateEventRequest.getEventId() != null) event.setId(updateEventRequest.getEventId());
        if (updateEventRequest.getPaid() != null) event.setPaid(updateEventRequest.getPaid());
        if (updateEventRequest.getParticipantLimit() != null) event.setParticipantLimit(updateEventRequest
                .getParticipantLimit());
        if (updateEventRequest.getTitle() != null) event.setTitle(updateEventRequest.getTitle());
    }

    public ParticipationRequest addParticipationRequestOfUser(Long userId, Long eventId) {
        if (!eventJpaRepository.existsById(eventId)) throw new EventNotFoundException();
        if (!userJpaRepository.existsById(userId)) throw new UserNotFoundException();
        ParticipationRequest participationRequest = ParticipationRequest.builder()
                .created(LocalDateTime.now())
                .event(eventId)
                .requester(userId)
                .status(Status.PENDING)
                .build();
        return participationRequestJpaRepository.save(participationRequest);
    }

    public List<Event> getEventsOfUser(Long userId, Integer from, Integer size) {
        Pageable page = OffsetLimitPageable.of(from, size);
        return eventJpaRepository.findAllByInitiatorId(userId, page);
    }

    public Event getEventOfCurrentUserById(Long userId, Long eventId) {
        return eventJpaRepository.findByIdAndInitiatorId(eventId, userId);

    }
}
