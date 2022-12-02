package ru.practicum.explorewithme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.explorewithme.model.participationrequest.ParticipationRequest;

public interface ParticipationRequestJpaRepository extends JpaRepository<ParticipationRequest, Long> {


}
