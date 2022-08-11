package com.obss.okan.express.domain.exception;

import org.aspectj.weaver.ast.Not;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class NotAuthorizedRequestException extends RuntimeException{
    public NotAuthorizedRequestException(String message){
        super(message);
    }
}
