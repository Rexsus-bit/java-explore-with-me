package ru.practicum.explorewithme.model.participationrequest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ParticipationRequestDto {

    private LocalDateTime created;
    private Long event;
    private Long id;
    private Long requester;
    private String status;

}
