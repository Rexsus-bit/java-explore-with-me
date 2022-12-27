package ru.practicum.statistics;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import ru.practicum.statistics.model.EndpointHitDto;
import ru.practicum.statistics.model.EndpointHit;
import ru.practicum.statistics.model.ViewStats;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class StatisticsController {

    private final StatisticsService statisticsService;
    private final ModelMapper modelMapper;

    @PostMapping("/hit")
    void saveStatisticsInfo(@RequestBody EndpointHitDto endpointHitDto) {
        EndpointHit endpointHit = modelMapper.map(endpointHitDto, EndpointHit.class);
        statisticsService.saveStatisticsInfo(endpointHit);
    }

    @GetMapping("/stats")
    List<ViewStats> getStatistic(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime start,
                                 @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime end,
                                 @RequestParam List<String> uris,
                                 @RequestParam(defaultValue = "false") Boolean unique) {
        return statisticsService.getStats(start, end, uris, unique);
    }
}
