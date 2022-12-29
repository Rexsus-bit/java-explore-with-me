package ru.practicum.explorewithme.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.practicum.explorewithme.model.event.Event;

import java.util.List;


@Repository
public interface EventJpaRepository extends JpaRepository<Event, Long> {

    List<Event> findAllByInitiatorId(long initiator_id, Pageable page);

    Event findByIdAndInitiatorId(Long id, long initiator_id);

}

