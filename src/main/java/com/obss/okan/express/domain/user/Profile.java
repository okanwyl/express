package com.obss.okan.express.domain.user;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
public class Profile {
    @Embedded
    private Email email;
    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "bio")
    private String bio;

    @Embedded
    private Image image;

    public Profile(Email email) {
        this(email, null, null);
    }

    private Profile(Email email, String bio, Image image) {
        this.email = email;
        this.bio = bio;
        this.image = image;
    }

    protected Profile() {}

    public Email getEmail() {
        return email;
    }

    public String getBio() {
        return bio;
    }

    public Image getImage() {
        return image;
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

    void changeImage(Image image) {
        this.image = image;
    }

    void changeName(String name) {
        this.name = name;
    }

    void changeSurname(String surname) {
        this.surname = surname;
    }

    void changeEmail(Email email) {
        this.email = email;
    }
}
