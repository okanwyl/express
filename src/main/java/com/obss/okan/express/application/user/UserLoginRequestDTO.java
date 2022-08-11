package com.obss.okan.express.application.user;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Value;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import java.util.NoSuchElementException;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.WRAPPER_OBJECT;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@JsonTypeName("user")
@JsonTypeInfo(include = WRAPPER_OBJECT, use = NAME)
@Value
class UserLoginRequestDTO {

    @Email
    @NotBlank(message = "Email cannot be empty")
    String email;
    @NotBlank(message = "Password cannot be empty")
    String password;
}
