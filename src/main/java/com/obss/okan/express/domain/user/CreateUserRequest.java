package com.obss.okan.express.domain.user;

public class CreateUserRequest {

    private final Email email;
    private final UserName userName;
    private final String rawPassword;
    private final String userType;

    public CreateUserRequest(Email email, UserName userName, String rawPassword, String userType) {
        this.email = email;
        this.userName = userName;
        this.rawPassword = rawPassword;
        this.userType = userType;
    }

    public Email getEmail() {
        return email;
    }

    public UserName getUserName() {
        return userName;
    }

    public String getUserType() {
        return userType;
    }

    public String getRawPassword() {
        return rawPassword;
    }
}
