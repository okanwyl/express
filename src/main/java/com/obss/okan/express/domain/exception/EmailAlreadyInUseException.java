package com.obss.okan.express.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class EmailAlreadyInUseException extends RuntimeException{
    public EmailAlreadyInUseException(String message){
        super(message);
    }
}
