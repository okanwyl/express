package com.obss.okan.express.domain.user;

import java.util.Optional;

import static java.util.Optional.ofNullable;

public class UserUpdateRequest {
    private final Email emailToUpdate;
    private final String nameToUpdate;
    private final String surnameToUpdate;
    private final String passwordToUpdate;
    private final Image imageToUpdate;
    private final String bioToUpdate;

    private UserUpdateRequest(UserUpdateRequestBuilder builder) {
        this.emailToUpdate = builder.emailToUpdate;
        this.nameToUpdate = builder.nameToUpdate;
        this.surnameToUpdate = builder.surnameToUpdate;
        this.passwordToUpdate = builder.passwordToUpdate;
        this.imageToUpdate = builder.imageToUpdate;
        this.bioToUpdate = builder.bioToUpdate;
    }

    public static UserUpdateRequestBuilder builder() {
        return new UserUpdateRequestBuilder();
    }

    Optional<Email> getEmailToUpdate() {
        return ofNullable(emailToUpdate);
    }

    Optional<String> getNameToUpdate() {
        return ofNullable(nameToUpdate);
    }

    Optional<String> getSurnameToUpdate() {
        return ofNullable(surnameToUpdate);
    }

    Optional<String> getPasswordToUpdate() {
        return ofNullable(passwordToUpdate);
    }

    Optional<Image> getImageToUpdate() {
        return ofNullable(imageToUpdate);
    }

    Optional<String> getBioToUpdate() {
        return ofNullable(bioToUpdate);
    }

    public static class UserUpdateRequestBuilder {
        private Email emailToUpdate;
        private String nameToUpdate;
        private String surnameToUpdate;
        private String passwordToUpdate;
        private Image imageToUpdate;
        private String bioToUpdate;

        public UserUpdateRequestBuilder emailToUpdate(Email emailToUpdate) {
            this.emailToUpdate = emailToUpdate;
            return this;
        }

        public UserUpdateRequestBuilder nameToUpdate(String nameToUpdate) {
            this.nameToUpdate = nameToUpdate;
            return this;
        }

        public UserUpdateRequestBuilder surnameToUpdate(String surnameToUpdate) {
            this.surnameToUpdate = surnameToUpdate;
            return this;
        }

        public UserUpdateRequestBuilder passwordToUpdate(String passwordToUpdate) {
            this.passwordToUpdate = passwordToUpdate;
            return this;
        }

        public UserUpdateRequestBuilder imageToUpdate(Image imageToUpdate) {
            this.imageToUpdate = imageToUpdate;
            return this;
        }

        public UserUpdateRequestBuilder bioToUpdate(String bioToUpdate) {
            this.bioToUpdate = bioToUpdate;
            return this;
        }

        public UserUpdateRequest build() {
            return new UserUpdateRequest(this);
        }
    }
}
