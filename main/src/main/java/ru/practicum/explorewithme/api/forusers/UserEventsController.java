package ru.practicum.explorewithme.api.forusers;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explorewithme.mapper.EventMapper;
import ru.practicum.explorewithme.model.event.*;
import ru.practicum.explorewithme.model.participationrequest.ParticipationRequestDto;
import ru.practicum.explorewithme.service.UserService;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/users")
public class UserEventsController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @GetMapping("/{userId}/events")
    public ResponseEntity<List<EventShortDto>> getEventsOfUser(@PathVariable Long userId,
                                                               @RequestParam(defaultValue = "0") Integer from,
                                                               @RequestParam(defaultValue = "10") Integer size) {
        return null;
    }

    @PatchMapping("/{userId}/events")
    public ResponseEntity<EventFullDto> updateEventOfCurrentUser(@PathVariable Long userId,
                                                    @RequestBody UpdateEventRequest updateEventRequest) {
        return null;
    }

    @PostMapping("/{userId}/events")
    public EventFullDto createEvent(@PathVariable Long userId, @Valid @RequestBody NewEventDto newEventDto) {
        Event event = userService.createEvent(userId, newEventDto);
        return EventMapper.toEventFullDto(event);
    }

    @GetMapping("/{userId}/events/{eventId}")
    public ResponseEntity<EventFullDto> getEventOfCurrentUserById(@PathVariable Long userId, @PathVariable Long eventId) {
        return null;
    }

    @PatchMapping("/{userId}/events/{eventId}")
    public ResponseEntity<EventFullDto> cancelEventAddedByCurrentUser(@PathVariable Long userId, @PathVariable Long eventId) {
        return null;
    }

    @GetMapping("/{userId}/events/{eventId}/requests")
    public ResponseEntity<ParticipationRequestDto> getInformationOnParticipationRequestToEventOfUser(@PathVariable Long userId,
                                                                                  @PathVariable Long eventId) {
        return null;
    }

    @PatchMapping("/{userId}/events/{eventId}/requests/{reqId}/confirm")
    public ResponseEntity<ParticipationRequestDto> confirmParticipationRequestOfUser(@PathVariable Long userId,
                                                                        @PathVariable Long eventId,
                                                                        @PathVariable Long reqId) {
        return null;
    }

    @PatchMapping("/{userId}/events/{eventId}/requests/{reqId}/reject")
    public ResponseEntity<ParticipationRequestDto> rejectParticipationRequestOfUser(@PathVariable Long userId,
                                                                        @PathVariable Long eventId,
                                                                        @PathVariable Long reqId) {
        return null;
    }

    @GetMapping("/{userId}/requests")
    public ResponseEntity<List<ParticipationRequestDto>> getInformationOnParticipationRequestsOfUser // TODO проверить возвращаемое значение
            (@PathVariable Long userId) {
        return null;
    }


    @PostMapping("/{userId}/requests")
    public ResponseEntity<ParticipationRequestDto> addParticipationRequestOfUser(@PathVariable Long userId,
                                                                                 @RequestParam Long eventId) {
        return null;
    }

    @PatchMapping("/{userId}/requests/{requestId}/cancel")
    public ResponseEntity<ParticipationRequestDto> cancelParticipationRequestByUser(@PathVariable Long userId,
                                                                                        @PathVariable Long requestId) {
        return null;
    }




}
