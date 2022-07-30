package com.obss.okan.express.application.user;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.obss.okan.express.domain.user.Email;
import com.obss.okan.express.domain.user.Image;
import com.obss.okan.express.domain.user.UserUpdateRequest;

import lombok.Value;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.WRAPPER_OBJECT;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;
import static java.util.Optional.ofNullable;

@JsonTypeName("user")
@JsonTypeInfo(include = WRAPPER_OBJECT, use = NAME)
@Value
class UserPutRequestDTO {

    String email;
    String name;
    String surname;
    String password;
    String bio;
    String image;

    UserUpdateRequest toUpdateRequest() {
        return UserUpdateRequest.builder().emailToUpdate(ofNullable(email).map(Email::new).orElse(null)).nameToUpdate(name).surnameToUpdate(surname).imageToUpdate(ofNullable(image).map(Image::new).orElse(null)).passwordToUpdate(password).bioToUpdate(bio).build();
    }
}
