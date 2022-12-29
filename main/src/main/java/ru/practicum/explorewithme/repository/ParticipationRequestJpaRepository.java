package ru.practicum.explorewithme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.practicum.explorewithme.model.Status;
import ru.practicum.explorewithme.model.event.Event;
import ru.practicum.explorewithme.model.participationrequest.ParticipationRequest;

import java.util.List;

@Repository
public interface ParticipationRequestJpaRepository extends JpaRepository<ParticipationRequest, Long> {

    List<ParticipationRequest> findAllByRequester_Id(long requesterId);

    List<ParticipationRequest> findByEvent(Event event);

    List<ParticipationRequest> findAllByStatusAndEvent(Status status, Event event);


}
