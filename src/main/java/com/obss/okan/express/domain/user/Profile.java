package com.obss.okan.express.domain.user;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Transient;

@Embeddable
public class Profile {
    @Embedded
    private UserName userName;

    @Column(name = "bio")
    private String bio;

    @Embedded
    private Image image;


    public Profile(UserName userName) {
        this(userName, null, null, false);
    }

    private Profile(UserName userName, String bio, Image image, boolean following) {
        this.userName = userName;
        this.bio = bio;
        this.image = image;
    }

    protected Profile() {
    }

    public UserName getUserName() {
        return userName;
    }

    public String getBio() {
        return bio;
    }

    public Image getImage() {
        return image;
    }


    void changeUserName(UserName userName) {
        this.userName = userName;
    }

    void changeBio(String bio) {
        this.bio = bio;
    }

    void changeImage(Image image) {
        this.image = image;
    }
}
