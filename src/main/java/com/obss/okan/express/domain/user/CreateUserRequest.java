package com.obss.okan.express.domain.user;

public class CreateUserRequest {

    private final Email email;
    private final String rawPassword;
    private final UserType userType;

    public CreateUserRequest(Email email, String rawPassword, String userType) {
        this.email = email;
        this.rawPassword = rawPassword;
        this.userType = UserType.lookup(Integer.parseInt(userType));
    }

    public Email getEmail() {
        return email;
    }

    public UserType getUserType() {
        return userType;
    }

    public String getRawPassword() {
        return rawPassword;
    }
}
