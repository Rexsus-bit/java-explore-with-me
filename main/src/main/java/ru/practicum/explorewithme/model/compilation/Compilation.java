package ru.practicum.explorewithme.model.compilation;

import lombok.*;
import ru.practicum.explorewithme.model.event.Event;
import ru.practicum.explorewithme.model.event.EventShortDto;

import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "compilations")
public class Compilation {
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "compilations_events_matches",
            joinColumns = {@JoinColumn(name = "compilation_id")},
            inverseJoinColumns = {@JoinColumn(name = "event_id")}) // TODO ЗАЧЕМ?
    private List<Event> events;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="compilation_id")
    private Long id;
    private Boolean pinned;
    private String title;
}
