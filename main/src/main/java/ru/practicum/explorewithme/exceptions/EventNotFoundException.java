package ru.practicum.explorewithme.exceptions;


public class EventNotFoundException extends RuntimeException {

    public EventNotFoundException() {
        super("Event was not found");
    }
}
