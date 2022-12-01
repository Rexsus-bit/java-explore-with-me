package ru.practicum.explorewithme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.explorewithme.model.event.Event;
import ru.practicum.explorewithme.model.location.Location;

public interface LocationJpaRepository extends JpaRepository<Location, Long> {
}
