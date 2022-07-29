package com.obss.okan.express.domain.user;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
public class Profile {

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "surname", nullable = false)
  private String surname;

  @Column(name = "bio")
  private String bio;

  @Embedded private Image image;

  public Profile(String name, String surname) {
    this(name, surname, null, null);
  }

  private Profile(String name, String surname, String bio, Image image) {
    this.name = name;
    this.surname = name;
    this.bio = bio;
    this.image = image;
  }

  protected Profile() {}

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
    this.surname = name;
  }
}
