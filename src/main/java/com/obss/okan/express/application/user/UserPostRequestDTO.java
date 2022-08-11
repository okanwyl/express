package com.obss.okan.express.application.user;

import com.obss.okan.express.domain.user.CreateUserRequest;
import com.obss.okan.express.domain.user.Email;
import com.obss.okan.express.domain.user.UserName;
import lombok.Value;


import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.WRAPPER_OBJECT;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("user")
@JsonTypeInfo(include = WRAPPER_OBJECT, use = NAME)
@Value
public class UserPostRequestDTO {

    @javax.validation.constraints.Email
    @NotBlank(message = "Email cannot be blank")
    String email;
    @NotBlank(message = "Password cannot be blank")
    String username;
    @NotBlank(message = "Password cannot be blank")
    String password;
    @NotBlank(message = "Type cannot be blank")
    @Min(value = 0, message = "Type cannot be lower than 0")
    @Max(value = 3, message = "Type cannot be higher than 3")
    String type;

    CreateUserRequest toSignUpRequest() {
        return new CreateUserRequest(new Email(email), new UserName(username), password, type);
    }

}
