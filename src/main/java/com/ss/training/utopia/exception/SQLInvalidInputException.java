package com.ss.training.utopia.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class SQLInvalidInputException extends IllegalStateException {
    public SQLInvalidInputException(String type) {
        super(type + " data was incorrectly passed.");
    }
}
