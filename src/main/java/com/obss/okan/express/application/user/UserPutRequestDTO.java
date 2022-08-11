package com.obss.okan.express.application.user;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.obss.okan.express.domain.user.Email;
import com.obss.okan.express.domain.user.UserUpdateRequest;
import lombok.Value;

import java.awt.*;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.WRAPPER_OBJECT;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;
import static java.util.Optional.ofNullable;

@JsonTypeName("user")
@JsonTypeInfo(include = WRAPPER_OBJECT, use = NAME)
@Value
class UserPutRequestDTO {

    String name;
    String surname;
    String password;
    String bio;

    UserUpdateRequest toUpdateRequest() {
        return UserUpdateRequest.builder()
                .nameToUpdate(name)
                .surnameToUpdate(surname)
                .passwordToUpdate(password).bioToUpdate(bio).build();
    }
}
