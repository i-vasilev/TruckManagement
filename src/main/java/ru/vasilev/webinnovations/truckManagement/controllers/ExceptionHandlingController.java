package ru.vasilev.webinnovations.truckManagement.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.vasilev.webinnovations.truckManagement.data.ErrorDetails;
import ru.vasilev.webinnovations.truckManagement.exceptions.EntityNotFoundException;

import java.util.Date;

@RestController
@ControllerAdvice
public class ExceptionHandlingController extends ResponseEntityExceptionHandler {

    @ExceptionHandler({RuntimeException.class})
    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public final ResponseEntity<ErrorDetails> handleRuntimeException(RuntimeException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),
                request.getDescription(false));
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        if (ex.getClass() == EntityNotFoundException.class) {
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(errorDetails, httpStatus);
    }
}
