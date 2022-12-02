package ru.practicum.explorewithme.model.participationrequest;

import lombok.*;
import ru.practicum.explorewithme.model.Status;

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "locations")
public class ParticipationRequest {

    private  LocalDateTime created;
    private  Long event;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private  Long requester;
    @Enumerated(EnumType.STRING)
    private Status status;


}
