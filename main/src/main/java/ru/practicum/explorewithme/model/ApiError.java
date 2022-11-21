package ru.practicum.explorewithme.model;

import org.hibernate.engine.spi.Status;

import java.time.LocalDateTime;

public class ApiError {
    private StackTraceElement errors; // TODO разобраться что это
    private String message;
    private String reason;
    private Status status; // TODO подумать над видом
    private LocalDateTime timestamp;
}
