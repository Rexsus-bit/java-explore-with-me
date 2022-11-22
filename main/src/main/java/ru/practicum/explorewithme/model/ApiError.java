package ru.practicum.explorewithme.model;

import org.hibernate.engine.spi.Status;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ApiError {
    private StackTraceElement errors; // TODO разобраться что это
    private String message;
    private String reason;
    private HttpStatus status; // TODO подумать над видом
    private LocalDateTime timestamp;
}
