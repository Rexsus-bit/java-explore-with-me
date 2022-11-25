package ru.practicum.explorewithme.api.forusers;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explorewithme.mapper.EventMapper;
import ru.practicum.explorewithme.mapper.ParticipationRequestMapper;
import ru.practicum.explorewithme.model.event.*;
import ru.practicum.explorewithme.model.participationrequest.ParticipationRequest;
import ru.practicum.explorewithme.model.participationrequest.ParticipationRequestDto;
import ru.practicum.explorewithme.service.UserService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/users")
public class UserEventsController {

    private final UserService userService;

    @GetMapping("/{userId}/events")
    public List<EventShortDto> getEventsOfUser(@PathVariable Long userId,
                                                               @RequestParam(defaultValue = "0") Integer from,
                                                               @RequestParam(defaultValue = "10") Integer size) {
        List<Event> events = userService.getEventsOfUser(userId, from, size);
        return events.stream().map(EventMapper::toEventShortDto).collect(Collectors.toList());

    }

    @PatchMapping("/{userId}/events")
    public EventFullDto updateEventOfCurrentUser(@PathVariable Long userId,
                                                    @RequestBody UpdateEventRequest updateEventRequest) {
        return EventMapper.toEventFullDto(userService.updateEventOfCurrentUser(userId, updateEventRequest));
    }

    @PostMapping("/{userId}/events")
    public EventFullDto createEvent(@PathVariable Long userId, @Valid @RequestBody NewEventDto newEventDto) {
        Event event = userService.createEvent(userId, newEventDto);
        return EventMapper.toEventFullDto(event);
    }

    @GetMapping("/{userId}/events/{eventId}")
    public EventFullDto getEventOfCurrentUserById(@PathVariable Long userId, @PathVariable Long eventId) {
       return  EventMapper.toEventFullDto(userService.getEventOfCurrentUserById(userId, eventId));
    }

    @PatchMapping("/{userId}/events/{eventId}")
    public EventFullDto cancelEventAddedByCurrentUser(@PathVariable Long userId, @PathVariable Long eventId) {
        return EventMapper.toEventFullDto(userService.cancelEventAddedByCurrentUser(userId, eventId));
    }

    @GetMapping("/{userId}/events/{eventId}/requests")
    public List<ParticipationRequestDto> getInformationOnParticipationRequestToEventOfUser(@PathVariable Long userId,
                                                                                  @PathVariable Long eventId) {
        List<ParticipationRequest> participationRequestList = userService
                .getInformationOnParticipationRequestToEventOfUser(userId, eventId);
        return participationRequestList.stream().map(ParticipationRequestMapper::toParticipationRequestDto)
                .collect(Collectors.toList());

    }

    @PatchMapping("/{userId}/events/{eventId}/requests/{reqId}/confirm")
    public ParticipationRequestDto confirmParticipationRequestOfUser(@PathVariable Long userId,
                                                                        @PathVariable Long eventId,
                                                                        @PathVariable Long reqId) {
        return ParticipationRequestMapper.toParticipationRequestDto(userService
                .confirmParticipationRequestOfUser(userId, eventId, reqId));
    }

    @PatchMapping("/{userId}/events/{eventId}/requests/{reqId}/reject")
    public ParticipationRequestDto rejectParticipationRequestOfUser(@PathVariable Long userId,
                                                                    @PathVariable Long eventId,
                                                                    @PathVariable Long reqId) {
        return ParticipationRequestMapper.toParticipationRequestDto(userService
                .rejectParticipationRequestOfUser(userId, eventId, reqId));
    }

    @GetMapping("/{userId}/requests")
    public List<ParticipationRequestDto> getInformationOnParticipationRequestsOfUser // TODO проверить возвращаемое значение
            (@PathVariable Long userId) {
                List<ParticipationRequest> participationRequestList = userService.getInformationOnParticipationRequestsOfUser(userId);
                return participationRequestList.stream()
                        .map(ParticipationRequestMapper::toParticipationRequestDto)
                        .collect(Collectors.toList());
    }

    @PostMapping("/{userId}/requests")
    public ParticipationRequestDto addParticipationRequestOfUser(@PathVariable Long userId, @RequestParam Long eventId) {
        return ParticipationRequestMapper.toParticipationRequestDto(userService.addParticipationRequestOfUser(userId, eventId));
    }

    @PatchMapping("/{userId}/requests/{requestId}/cancel")
    public ParticipationRequestDto cancelParticipationRequestByUser(@PathVariable Long userId,
                                                                    @PathVariable Long requestId) {
        return ParticipationRequestMapper.toParticipationRequestDto(userService
                .cancelParticipationRequestByUser(userId, requestId));
    }



}
