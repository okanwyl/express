package com.obss.okan.express.application.user;

import com.obss.okan.express.domain.user.CreateUserRequest;
import com.obss.okan.express.domain.user.Email;
import com.obss.okan.express.domain.user.UserType;
import lombok.Value;


import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.WRAPPER_OBJECT;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("user")
@JsonTypeInfo(include = WRAPPER_OBJECT, use = NAME)
@Value
public class UserPostRequestDTO {

    @javax.validation.constraints.Email
    @NotBlank
    String email;
    @NotBlank
    String password;
    @NotBlank
    String type;

    CreateUserRequest toSignUpRequest() {
        return new CreateUserRequest(new Email(email), password, type);
    }

}
