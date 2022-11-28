package ru.practicum.explorewithme.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.practicum.explorewithme.model.event.Event;
import ru.practicum.explorewithme.model.event.State;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface EventJpaRepository extends JpaRepository<Event, Long> {

    @Query("from Event event " +
            "where event.initiator.id in ?1 or ?1 is null " +
            "and event.state in ?2 or ?2 is null " +
            "and event.category.id in ?3 or ?3 is null " +
            "and event.eventDate between ?4 and ?5")
    List<Event> findEvents(List<Long> users, List<State> states, List<Long> categories, LocalDateTime rangeStart
            , LocalDateTime rangeEnd, Pageable page);
}