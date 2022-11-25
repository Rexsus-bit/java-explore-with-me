package ru.practicum.explorewithme;

import org.springframework.http.ResponseEntity;
import ru.practicum.explorewithme.model.event.Event;
import ru.practicum.explorewithme.model.event.EventFullDto;
import ru.practicum.explorewithme.model.user.User;
import ru.practicum.explorewithme.model.user.UserDto;

import java.util.List;

public class Mapper {
    public ResponseEntity<List<EventFullDto>> toEventsFullDtoList(List<Event> eventsList) {
        return null;
    }

    public ResponseEntity<UserDto> toUserDto(User user) {
        return null;
    }
}
