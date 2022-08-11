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
        String username;
        String name;
        String surname;
        String bio;

        public static ProfileModelNested fromProfile(Profile profile) {
            return new ProfileModelNested(profile.getUserName().toString(), profile.getName(), profile.getSurname(),
                    profile.getBio());

        }
    }
}
