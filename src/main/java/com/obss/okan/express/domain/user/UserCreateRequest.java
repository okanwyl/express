package com.obss.okan.express.domain.user;

public class UserCreateRequest {

    private final Email email;
    private final UserName userName;
    private final String rawPassword;

    public UserCreateRequest(Email email, UserName userName, String rawPassword) {
        this.email = email;
        this.userName = userName;
        this.rawPassword = rawPassword;
    }

    public Email getEmail() {
        return email;
    }

    public UserName getUserName() {
        return userName;
    }

    public String getRawPassword() {
        return rawPassword;
    }

}
