package ru.practicum.explorewithme.model.event;


import lombok.Getter;
import lombok.Setter;
import ru.practicum.explorewithme.model.location.Location;

import java.time.LocalDateTime;

@Getter
@Setter
public class AdminUpdateEventRequest {

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


