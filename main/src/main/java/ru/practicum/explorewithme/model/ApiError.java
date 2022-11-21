package ru.practicum.explorewithme.model;

import java.time.LocalDateTime;

public class ApiError {
    private StackTraceElement errors; // TODO разобраться что это
    private String message;
    private String reason;
    private String status;
    private LocalDateTime timestamp;
}
