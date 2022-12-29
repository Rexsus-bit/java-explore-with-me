package ru.practicum.explorewithme.mapper;

import ru.practicum.explorewithme.model.category.CategoryDto;
import ru.practicum.explorewithme.model.event.Event;
import ru.practicum.explorewithme.model.event.EventFullDto;
import ru.practicum.explorewithme.model.event.EventShortDto;
import ru.practicum.explorewithme.model.user.UserShortDto;

public class EventMapper {

    public static EventShortDto toEventShortDto(Event event) {

        CategoryDto categoryDto = CategoryDto.builder()
                .id(event.getCategory().getId())
                .name(event.getCategory().getName())
                .build();

        UserShortDto userShortDto = UserShortDto.builder()
                .id(event.getInitiator().getId())
                .name(event.getInitiator().getName())
                .build();

        return EventShortDto.builder()
                .annotation(event.getAnnotation())
                .category(categoryDto)
                .confirmedRequests(event.getConfirmedRequests())
                .eventDate(event.getEventDate())
                .id(event.getId())
                .initiator(userShortDto)
                .paid(event.getPaid())
                .title(event.getTitle())
                .views(event.getViews())
                .build();
    }

    public static EventFullDto toEventFullDto(Event event) {
        CategoryDto categoryDto = CategoryDto.builder()
                .id(event.getCategory().getId())
                .name(event.getCategory().getName())
                .build();

        UserShortDto userShortDto = UserShortDto.builder()
                .id(event.getInitiator().getId())
                .name(event.getInitiator().getName())
                .build();

        return EventFullDto.builder()
                .annotation(event.getAnnotation())
                .category(categoryDto)
                .confirmedRequests(event.getConfirmedRequests())
                .createdOn(event.getCreatedOn())
                .description(event.getDescription())
                .eventDate(event.getEventDate())
                .id(event.getId())
                .initiator(userShortDto)
                .location(event.getLocation())
                .paid(event.getPaid())
                .participantLimit(event.getParticipantLimit())
                .publishedOn(event.getPublishedOn())
                .requestModeration(event.getRequestModeration())
                .state(event.getState())
                .title(event.getTitle())
                .views(event.getViews())
                .build();

    }

}
