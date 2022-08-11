package com.obss.okan.express.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class SlugAlreadyInUseException extends RuntimeException {
    public SlugAlreadyInUseException(String message) {
        super(message);
    }
}

//@ResponseStatus(HttpStatus.IM_USED)
//public class EmailAlreadyInUseException extends RuntimeException{
//    public EmailAlreadyInUseException(String message){
//        super(message);
//    }
//}
