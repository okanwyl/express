package com.obss.okan.express.application.user;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.obss.okan.express.domain.user.User;
import lombok.Value;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.WRAPPER_OBJECT;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;
import static java.lang.String.valueOf;

@JsonTypeName("user")
@JsonTypeInfo(include = WRAPPER_OBJECT, use = NAME)
@Value
class UserModel {
    String email;
    String username;
    String token;

    static UserModel fromUserAndToken(User user, String token) {
        return new UserModel(valueOf(user.getEmail()), valueOf(user.getProfile().getUserName()), token);
    }
}

