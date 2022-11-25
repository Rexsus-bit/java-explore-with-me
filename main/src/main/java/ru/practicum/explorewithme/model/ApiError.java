package ru.practicum.explorewithme.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.engine.spi.Status;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class ApiError {
    private StackTraceElement [] errors;
    private String message;
    private String reason;
    private HttpStatus status;
    private LocalDateTime timestamp;

}
