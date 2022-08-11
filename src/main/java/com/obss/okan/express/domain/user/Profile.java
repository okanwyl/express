package com.obss.okan.express.domain.user;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.awt.*;

@Embeddable
public class Profile {
    @Embedded
    private Email email;
    @Embedded
    private UserName userName;
    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "bio")
    private String bio;


    public Profile(Email email, UserName userName) {
        this(email, userName, "", "", "");
    }

    private Profile(Email email, UserName userName, String name, String surname, String bio) {
        this.email = email;
        this.userName = userName;
        this.bio = bio;
        this.name = name;
        this.surname = surname;
    }

    protected Profile() {
    }

    public Email getEmail() {
        return email;
    }

    public UserName getUserName() {
        return userName;
    }

    public String getBio() {
        return bio;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    void changeBio(String bio) {
        this.bio = bio;
    }

    void changeName(String name) {
        this.name = name;
    }

    void changeSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString(){
        return name + "," + surname + "," + email + "," + userName;
    }

}
