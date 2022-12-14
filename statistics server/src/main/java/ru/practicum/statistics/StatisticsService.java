package ru.practicum.statistics;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.statistics.model.EndpointHit;
import ru.practicum.statistics.model.ViewStats;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticsService {

    private final StatisticsJpaRepository statisticsJpaRepository;

    public void saveStatisticsInfo(EndpointHit endpointHit) {
        statisticsJpaRepository.save(endpointHit);
    }

    public List<ViewStats> getStats(LocalDateTime start, LocalDateTime end, List<String> uris, boolean unique) {
        if (unique) {
            return statisticsJpaRepository.getStatsByCriteriaUnique(start, end, uris);
        } else {
            return statisticsJpaRepository.getStatsByCriteria(start, end, uris);
        }
    }
}
