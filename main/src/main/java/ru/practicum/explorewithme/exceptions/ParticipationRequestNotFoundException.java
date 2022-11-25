package ru.practicum.explorewithme.exceptions;

public class ParticipationRequestNotFoundException extends RuntimeException {
    public ParticipationRequestNotFoundException() {
        super("ParticipationRequest was not found");
    }
}
