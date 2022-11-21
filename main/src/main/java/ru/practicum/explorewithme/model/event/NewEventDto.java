package ru.practicum.explorewithme.model.event;

import ru.practicum.explorewithme.model.category.CategoryDto;
import ru.practicum.explorewithme.model.location.Location;
import ru.practicum.explorewithme.model.user.UserShortDto;

import java.time.LocalDateTime;

public class NewEventDto {

    private String annotation;
    private Long category;
    private String description;
    private LocalDateTime eventDate;
    private Location location;
    private Boolean paid;
    private Integer participantLimit;
    private Boolean requestModeration;
    private String title;

}
