package com.obss.okan.express.domain.user;

public class CreateUserRequest {

    private final Email email;

    private final String name;

    private final String surname;


    private final String rawPassword;
    private final UserType userType;

    public CreateUserRequest(Email email, String name, String surname, String rawPassword, UserType userType) {
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.rawPassword = rawPassword;
        this.userType = userType;
    }

    public Email getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public UserType getUserType() {
        return userType;
    }

    public String getRawPassword() {
        return rawPassword;
    }
}
