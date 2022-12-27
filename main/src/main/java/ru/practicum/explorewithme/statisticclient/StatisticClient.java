package ru.practicum.explorewithme.statisticclient;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;

import org.springframework.web.client.RestTemplate;
import ru.practicum.statistics.model.EndpointHitDto;
import ru.practicum.statistics.model.ViewStats;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class StatisticClient {

    private final RestTemplate restTemplate;

    @Value("${statistics.url}")
    private String url;

    public StatisticClient() {
        this.restTemplate = new RestTemplate();
    }

   public void sendStatisticsInfo (String app, String uri, String ip){
        HttpEntity<EndpointHitDto> request = new HttpEntity<>( new EndpointHitDto(null
                , app, uri, ip, LocalDateTime.now()));



        restTemplate.postForObject(url + "/hit", request, EndpointHitDto.class);
    }

    List<ViewStats> getStatistic(LocalDateTime start, LocalDateTime end, List<String> uris
            , Boolean unique){
        Map<String, String> vars = new HashMap<>();
        vars.put("start", start.toString());
        vars.put("end", end.toString());
        vars.put("uris", String.join(",", uris));
        vars.put("unique", unique.toString());
        ViewStats[] viewStats = restTemplate.getForObject(url + "/stats", ViewStats[].class, vars);
        return new ArrayList<ViewStats>(Arrays.asList(viewStats));
    }

}
