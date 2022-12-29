package ru.practicum.explorewithme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.practicum.explorewithme.model.event.Event;
import ru.practicum.explorewithme.model.location.Location;

@Repository
public interface LocationJpaRepository extends JpaRepository<Location, Long> {
}
