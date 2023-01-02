package ru.practicum.explorewithme.model.participationrequest;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParticipationRequestDto {

    private LocalDateTime created;
    private Long event;
    private Long id;
    private Long requester;
    private String status;

}
