package ru.practicum.explorewithme.exceptions;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.practicum.explorewithme.model.ApiError;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionsHandler {

    private final String UNMEET_CONDITIONS = "For the requested operation the conditions are not met";

    public ExceptionsHandler() {
    }

    @ExceptionHandler(value = {CategoryNotFoundException.class, UserNotFoundException.class, CompilationNotFoundException.class })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError handleNotFound(final RuntimeException ex) {
        return new ApiError(ex.getStackTrace(), ex.getMessage(), UNMEET_CONDITIONS, HttpStatus.NOT_FOUND, LocalDateTime.now());

    }


//
//    @ExceptionHandler
//    protected ResponseEntity<Object> handleHttpMessageNotReadable(final HttpMessageNotReadableException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
//        final String bodyOfResponse = "This should be application specific";
//        // ex.getCause() instanceof JsonMappingException, JsonParseException // for additional information later on
//        return handleExceptionInternal(ex, bodyOfResponse, headers, HttpStatus.BAD_REQUEST, request);
//    }
//





    // 409

//    @ExceptionHandler({ InvalidDataAccessApiUsageException.class, DataAccessException.class })
//    protected ResponseEntity<Object> handleConflict(final RuntimeException ex, final WebRequest request) {
//        final String bodyOfResponse = "This should be application specific";
//        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
//    }

    // 412

    // 500

//    @ExceptionHandler({ NullPointerException.class, IllegalArgumentException.class, IllegalStateException.class })
//    /*500*/public ResponseEntity<Object> handleInternal(final RuntimeException ex, final WebRequest request) {
//        logger.error("500 Status Code", ex);
//        final String bodyOfResponse = "not";
//        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
//    }


}
