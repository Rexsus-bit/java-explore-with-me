package ru.practicum.explorewithme.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.engine.spi.Status;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class ApiError {
    private StackTraceElement [] errors; // TODO разобраться что это
    private String message;
    private String reason;
    private HttpStatus status; // TODO подумать над видом
    private LocalDateTime timestamp;

}
