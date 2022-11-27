package ru.practicum.explorewithme.model.event;

import lombok.Getter;
import lombok.Setter;
import ru.practicum.explorewithme.model.category.CategoryDto;
import ru.practicum.explorewithme.model.location.Location;
import ru.practicum.explorewithme.model.user.UserShortDto;

import java.time.LocalDateTime;

@Getter
@Setter
public class EventFullDto {

    private String annotation;
    private CategoryDto categoryDto;//TODO
    private Long confirmedRequests;
    private LocalDateTime createdOn;
    private String description;
    private LocalDateTime eventDate;
    private Long id;
    private UserShortDto initiator; //TODO
    private Location location;
    private Boolean paid;
    private Integer participantLimit;
    private LocalDateTime publishedOn;
    private Boolean requestModeration;
    private State state;
    private String title;
    private Long views;

}
