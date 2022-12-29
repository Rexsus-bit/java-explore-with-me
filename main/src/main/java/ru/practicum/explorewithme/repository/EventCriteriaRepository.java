package ru.practicum.explorewithme.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.practicum.explorewithme.model.event.Event;
import ru.practicum.explorewithme.model.event.State;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.Predicate;

@Repository
@RequiredArgsConstructor
public class EventCriteriaRepository {

    private final EntityManager entityManager;

    public List<Event> findEventsByCustomCriteria(List<Long> users, List<State> states, List<Long> categories,
                                                  LocalDateTime rangeStart, LocalDateTime rangeEnd, Integer from,
                                                  Integer size, String text) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Event> query = cb.createQuery(Event.class);
        Root<Event> root = query.from(Event.class);

        List<Predicate> predicates = new ArrayList<>();

        if (users != null) {
            predicates.add(cb.in(root.get("initiator").get("id")).value(users));
        }
        if (states != null) {
            predicates.add(cb.in(root.get("state")).value(states));
        }
        if (categories != null) {
            predicates.add(cb.in(root.get("category").get("id")).value(categories));
        }
        if (rangeStart != null && rangeEnd != null) {
            predicates.add(cb.between(root.get("eventDate"), rangeStart, rangeEnd));
        }
        if (text != null) {
            predicates.add(cb.or(cb.like(cb.upper(root.get("annotation")),
                    "%" + text.toUpperCase() + "%"), cb.like(cb.upper(root.get("description")),
                    "%" + text.toUpperCase() + "%")));
        }

        return entityManager.createQuery(query.select(root)
                        .where(cb.and(predicates.toArray(new Predicate[]{}))))
                .setFirstResult(from).setMaxResults(size).getResultList();
    }
}
