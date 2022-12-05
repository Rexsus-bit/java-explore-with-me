package ru.practicum.explorewithme.mapper;

import ru.practicum.explorewithme.model.participationrequest.ParticipationRequest;
import ru.practicum.explorewithme.model.participationrequest.ParticipationRequestDto;

public class ParticipationRequestMapper {

    public static ParticipationRequestDto toParticipationRequestDto(ParticipationRequest participationRequest){
       return ParticipationRequestDto.builder()
                .created(participationRequest.getCreated())
                .event(participationRequest.getEvent().getId())
                .id(participationRequest.getId())
                .requester(participationRequest.getRequester().getId())
                .status(participationRequest.getStatus().toString())
                .build();
    }

}
