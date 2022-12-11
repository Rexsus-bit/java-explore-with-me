package ru.practicum.statistics;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.statistics.model.EndpointHit;
import ru.practicum.statistics.model.ViewStats;

import javax.swing.text.View;
import java.time.LocalDateTime;
import java.util.List;

interface StatisticsJpaRepository extends JpaRepository<EndpointHit, Long> {

    @Query(" SELECT e.app AS app, e.uri AS uri, COUNT(e.id) AS hits " +
            "FROM EndpointHit e " +
            " WHERE e.uri IN :uris " +
            " AND e.timeStamp BETWEEN :start AND :end " +
            " GROUP BY e.app, e.uri, e.ip ")
    List<ViewStats> getStatsByCriteriaUnique(LocalDateTime start, LocalDateTime end, List<String> uris);

    @Query(" SELECT e.app AS app, e.uri AS uri, COUNT(e.id) AS hits " +
            "FROM EndpointHit e " +
            " WHERE e.uri IN :uris " +
            " AND e.timeStamp BETWEEN :start AND :end " +
            " GROUP BY e.app, e.uri ")
    List<ViewStats> getStatsByCriteria(LocalDateTime start,LocalDateTime end, List<String> uris);

}
