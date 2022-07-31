package com.obss.okan.express.domain.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserTest {
    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private Email emailMock;

    @Mock
    private Password passwordMock;


    @Test
    void when_create_user_getImage_return_null() {
        final var user = User.of(emailMock, "name", "surname", passwordMock, UserType.DEVELOPER);

        assertThat(user.getImage()).isNull();
    }

    @Test
    void when_create_user_getBio_return_null() {
        final var user = User.of(emailMock, "name", "surname", passwordMock, UserType.DEVELOPER);

        assertThat(user.getBio()).isNull();
    }

    @Test
    void when_user_have_different_email_expect_not_equal_and_hashCode(
            @Mock Email otherEmail, @Mock Password otherPassword) {
        final var user = User.of(emailMock, "name", "surname", passwordMock, UserType.DEVELOPER);
        final var userWithSameEmail = User.of(otherEmail, "otherName", "otherSurname", otherPassword, UserType.DEVELOPER);

        assertThat(userWithSameEmail)
                .isNotEqualTo(user)
                .extracting(User::hashCode)
                .isNotEqualTo(user.hashCode());
    }

    @Test
    void when_user_have_same_email_expect_equal_and_hashCode(@Mock Password otherPassword) {
        final var user = User.of(emailMock, "name", "surname", passwordMock, UserType.DEVELOPER);
        final var userWithSameEmail = User.of(emailMock, "otherName", "otherSurname", otherPassword, UserType.DEVELOPER);

        assertThat(userWithSameEmail)
                .isEqualTo(user)
                .hasSameHashCodeAs(user);
    }

    @Test
    void when_matches_password_expect_password_matches_password() {
        final var user = User.of(emailMock, "name", "surname", passwordMock, UserType.DEVELOPER);

        user.matchesPassword("some-password", passwordEncoder);

        verify(passwordMock, times(1)).matchesPassword("some-password", passwordEncoder);
    }

    @Test
    void when_changeEmail_expect_getEmail_return_new_email(@Mock Email emailToChange) {
        final var user = User.of(emailMock, "name", "surname", passwordMock, UserType.DEVELOPER);

        user.changeEmail(emailToChange);

        assertThat(user.getEmail()).isEqualTo(emailToChange);
    }

    @Test
    void when_changePassword_expect_matchesPassword_matches_new_password(@Mock Password passwordToChange) {
        final var user = User.of(emailMock, "name", "surname", passwordMock, UserType.DEVELOPER);

        user.changePassword(passwordToChange);

        user.matchesPassword("some-password", passwordEncoder);
        verify(passwordToChange, times(1)).matchesPassword("some-password", passwordEncoder);
    }

    @Test
    void when_changeName_expect_getName_return_new_name() {
        final var user = User.of(emailMock, "name", "surname", passwordMock, UserType.DEVELOPER);

        user.changeName("nameToChange");

        assertThat(user.getName()).isEqualTo("nameToChange");
    }

    @Test
    void when_changeSurname_expect_getSurname_return_new_surname() {
        final var user = User.of(emailMock, "name", "surname", passwordMock, UserType.DEVELOPER);

        user.changeSurname("surnameToChange");

        assertThat(user.getSurname()).isEqualTo("surnameToChange");
    }

    @Test
    void when_changeBio_expect_getBio_return_new_bio() {
        final var user = User.of(emailMock, "name", "surname", passwordMock, UserType.DEVELOPER);

        user.changeBio("new bio");

        assertThat(user.getBio()).isEqualTo("new bio");
    }

    @Test
    void when_changeImage_expect_getImage_return_new_image(@Mock Image imageToChange) {
        final var user = User.of(emailMock, "name", "surname", passwordMock, UserType.DEVELOPER);

        user.changeImage(imageToChange);

        assertThat(user.getImage()).isEqualTo(imageToChange);
    }

}
