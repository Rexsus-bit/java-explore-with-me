package ru.practicum.explorewithme.model.event;

import lombok.*;
import ru.practicum.explorewithme.model.category.Category;
import ru.practicum.explorewithme.model.location.Location;
import ru.practicum.explorewithme.model.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "events")
public class Event {

    @Column(length = 1024)
    private String annotation;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;
    @Column(name = "confirmed_requests")
    private Long confirmedRequests;
    @Column(name = "created_on")
    private LocalDateTime createdOn;
    @Column(length = 10000)
    private String description;
    @Column(name = "event_date")
    private LocalDateTime eventDate;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="event_id")
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "initiator_id")
    private User initiator;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id")
    private Location location;
    private Boolean paid;
    @Column(name = "participant_limit")
    private Integer participantLimit;
    @Column(name="published_on")
    private LocalDateTime publishedOn;
    @Column(name="request_moderation")
    private Boolean requestModeration;
    @Enumerated(EnumType.STRING)
    private State state;
    @Column(length = 1024)
    private String title;
    private Long views;
}
