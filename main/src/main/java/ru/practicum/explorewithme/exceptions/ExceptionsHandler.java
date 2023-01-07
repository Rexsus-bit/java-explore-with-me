package ru.practicum.explorewithme.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.practicum.explorewithme.model.ApiError;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionsHandler {

    private final String unmeetConditions = "For the requested operation the conditions are not met";

    public ExceptionsHandler() {
    }

    @ExceptionHandler(value = {CategoryNotFoundException.class, UserNotFoundException.class,
            CompilationNotFoundException.class, ParticipationRequestNotFoundException.class,
            CommentNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError handleNotFound(final RuntimeException ex) {
        return new ApiError(ex.getStackTrace(), ex.getMessage(), unmeetConditions,
                HttpStatus.NOT_FOUND, LocalDateTime.now());

    }
}
