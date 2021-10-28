package com.ss.training.utopia.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class SQLInvalidKeyPairException extends IllegalStateException {
    public SQLInvalidKeyPairException(String type) {
        super(type + " ID does not match target ID.");
    }
}
