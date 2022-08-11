package com.obss.okan.express.domain.user;

import java.awt.*;
import java.util.Optional;

import static java.util.Optional.ofNullable;

public class UserUpdateRequest {
    private final String nameToUpdate;
    private final String surnameToUpdate;
    private final String passwordToUpdate;
    private final String bioToUpdate;

    private UserUpdateRequest(UserUpdateRequestBuilder builder) {
        this.nameToUpdate = builder.nameToUpdate;
        this.surnameToUpdate = builder.surnameToUpdate;
        this.passwordToUpdate = builder.passwordToUpdate;
        this.bioToUpdate = builder.bioToUpdate;
    }

    public static UserUpdateRequestBuilder builder() {
        return new UserUpdateRequestBuilder();
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

    Optional<String> getBioToUpdate() {
        return ofNullable(bioToUpdate);
    }

    public static class UserUpdateRequestBuilder {
        private String nameToUpdate;
        private String surnameToUpdate;
        private String passwordToUpdate;
        private String bioToUpdate;


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

        public UserUpdateRequestBuilder bioToUpdate(String bioToUpdate) {
            this.bioToUpdate = bioToUpdate;
            return this;
        }

        public UserUpdateRequest build() {
            return new UserUpdateRequest(this);
        }
    }
}
