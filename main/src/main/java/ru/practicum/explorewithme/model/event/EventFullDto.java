package ru.practicum.explorewithme.model.event;

import ru.practicum.explorewithme.model.category.CategoryDto;
import ru.practicum.explorewithme.model.location.Location;
import ru.practicum.explorewithme.model.user.UserShortDto;

import java.time.LocalDateTime;

public class EventFullDto {

    private String annotation;
    private CategoryDto categoryDto;
    private Long confirmedRequests;
    private LocalDateTime createdOn;
    private String description;
    private LocalDateTime eventDate;
    private Long id;
    private UserShortDto initiator;
    private Location location;
    private Boolean paid;
    private Integer participantLimit;
    private LocalDateTime publishedOn;
    private Boolean requestModeration;
    private State state;
    private String title;
    private Long views;

}
