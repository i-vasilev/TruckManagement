package ru.vasilev.webinnovations.truckManagement.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Some fields is absent!")
public class FieldsIsAbsentException extends RuntimeException {
    public FieldsIsAbsentException(String message) {
        super(message);
    }
}
