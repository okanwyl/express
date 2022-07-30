package com.obss.okan.express.application.user;

import com.obss.okan.express.domain.user.Profile;
import lombok.Value;

import static java.lang.String.valueOf;

@Value
public class ProfileModel {

    ProfileModelNested profile;


    public static ProfileModel fromProfile(Profile profile) {
        return new ProfileModel(ProfileModelNested.fromProfile(profile));
    }


    @Value
    public static class ProfileModelNested {
        String name;
        String surname;
        // String email;
        String bio;
        String image;

        public static ProfileModelNested fromProfile(Profile profile) {
            return new ProfileModelNested(profile.getName(), profile.getSurname(),
                    // profile.getEmail(),
                    profile.getBio(), valueOf(profile.getImage()));

        }
    }
}