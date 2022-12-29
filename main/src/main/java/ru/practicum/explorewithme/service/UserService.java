package ru.practicum.explorewithme.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.explorewithme.exceptions.*;
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
    private final ParticipationRequestJpaRepository participationRequestJpaRepository;

    @Transactional
    public Event createEvent(Long userId, NewEventDto newEventDto) {
        Event event = modelMapper.map(newEventDto, Event.class);

        event.setInitiator(userJpaRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new));
        event.setCategory(categoryJpaRepository.findById(newEventDto.getCategory())
                .orElseThrow(CategoryNotFoundException::new));
        event.setViews(0L);
        event.setConfirmedRequests(0L);
        event.setState(State.PENDING);
        event.setCreatedOn(LocalDateTime.now());
        return eventJpaRepository.save(event);
    }

    @Transactional
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

    @Transactional
    public ParticipationRequest addParticipationRequestOfUser(Long userId, Long eventId) {
        if (!eventJpaRepository.existsById(eventId)) throw new EventNotFoundException();
        if (!userJpaRepository.existsById(userId)) throw new UserNotFoundException();
        ParticipationRequest participationRequest = ParticipationRequest.builder()
                .created(LocalDateTime.now())
                .event(eventJpaRepository.findById(eventId).orElseThrow(EventNotFoundException::new))
                .requester(userJpaRepository.findById(userId).orElseThrow(UserNotFoundException::new))
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

    public ParticipationRequest cancelParticipationRequestByUser(Long userId, Long requestId) {
        ParticipationRequest participationRequest = participationRequestJpaRepository.findById(requestId)
                .orElseThrow(ParticipationRequestNotFoundException::new);
        User user = userJpaRepository.findById(userId).orElseThrow(EventNotFoundException::new);
        if (!participationRequest.getRequester().equals(user)) throw new ValidationException();
        participationRequest.setStatus(Status.CANCELED);
        return participationRequestJpaRepository.save(participationRequest);

    }

    public List<ParticipationRequest> getInformationOnParticipationRequestsOfUser(Long userId) {
        return participationRequestJpaRepository.findAllByRequester_Id(userId);
    }

    public List<ParticipationRequest> getInformationOnParticipationRequestToEventOfUser(Long userId, Long eventId) {
        Event event = eventInitiatorCheck(userId, eventId);
        return participationRequestJpaRepository.findByEvent(event);
    }

    private Event eventInitiatorCheck(Long userId, Long eventId) {
        Event event = eventJpaRepository.findById(eventId).orElseThrow(EventNotFoundException::new);
        User user = userJpaRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        if (!event.getInitiator().equals(user)) throw new ValidationException();
        return event;
    }

    public ParticipationRequest confirmParticipationRequestOfUser(Long userId, Long eventId, Long reqId) {
        Event event = eventInitiatorCheck(userId, eventId);
        ParticipationRequest participationRequest = participationRequestJpaRepository.findById(reqId)
                    .orElseThrow(ParticipationRequestNotFoundException::new);
        if (!event.getRequestModeration() || event.getParticipantLimit() == 0) {
            return participationRequest;
        }

        int quantity = participationRequestJpaRepository.findAllByStatusAndEvent(Status.CONFIRMED, event).size();
        if (event.getParticipantLimit() > 0 && quantity >= event.getParticipantLimit()) {
            throw new ValidationException();
        }
        participationRequest.setStatus(Status.CONFIRMED);
        participationRequest = participationRequestJpaRepository.save(participationRequest);
        quantity++;
        // если при подтверждении данной заявки, лимит заявок для события исчерпан, то все неподтверждённые заявки необходимо отклонить
        if (quantity >= event.getParticipantLimit()) {
            participationRequestJpaRepository.findAllByStatusAndEvent(Status.PENDING, event)
                    .stream().peek(r -> r.setStatus(Status.CANCELED)).forEach(participationRequestJpaRepository::save);
        }
        return participationRequest;
    }

    public Event cancelEventAddedByCurrentUser(Long userId, Long eventId) {
        Event event = eventInitiatorCheck(userId, eventId);
        event.setState(State.CANCELED);
        return eventJpaRepository.save(event);
    }

    public ParticipationRequest rejectParticipationRequestOfUser(Long userId, Long eventId, Long reqId) {
        Event event = eventInitiatorCheck(userId, eventId);
        ParticipationRequest participationRequest = participationRequestJpaRepository.findById(reqId)
                .orElseThrow(ParticipationRequestNotFoundException::new);
        if (!event.equals(participationRequest.getEvent())) throw new ValidationException();
        participationRequest.setStatus(Status.REJECTED);
        return participationRequestJpaRepository.save(participationRequest);
    }
}
