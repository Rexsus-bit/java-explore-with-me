package ru.practicum.explorewithme.model.event;
import lombok.*;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateEventRequest {

    @Size(min = 20, max = 2000)
    private String annotation;
    private Long category;
    @Size(min = 20, max = 7000)
    private String description;
    private LocalDateTime eventDate;
    @Min(1)
    private Long eventId;
    private Boolean paid;
    @Min(0)
    private Integer participantLimit;
    @Size(min = 3, max = 120)
    private String title;

}
