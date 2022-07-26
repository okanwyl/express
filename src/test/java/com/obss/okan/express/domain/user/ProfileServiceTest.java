package com.obss.okan.express.domain.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;

//import static com.obss.okan.express.domain.user.User.of;
import static java.util.Optional.of;

import static java.util.Optional.empty;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.BDDMockito.then;


@ExtendWith(MockitoExtension.class)
class ProfileServiceTest {
    private ProfileService profileService;

    @Mock
    private UserFindService userFindService;

    @BeforeEach
    private void initializeService() {
        this.profileService = new ProfileService(userFindService);
    }

    @Test
    void when_viewProfile_with_viewer_not_exists_expect_NoSuchElementException(@Mock UserName userName) {
        when(userFindService.findById(1L)).thenReturn(empty());

        assertThatThrownBy(() ->
                profileService.viewProfile(1L, userName)
        ).isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void when_viewProfile_with_not_exists_username_expect_NoSuchElementException(@Mock User user, @Mock UserName userName) {
        when(userFindService.findById(1L)).thenReturn(of(user));
        when(userFindService.findByUsername(userName)).thenReturn(empty());

        assertThatThrownBy(() ->
                profileService.viewProfile(1L, userName)
        ).isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void when_viewProfile_expect_viewer_view_found_user(@Mock UserName userName,
                                                        @Mock User viewer, @Mock User userToView,
                                                        @Mock Profile profile) {
        given(userFindService.findById(1L)).willReturn(of(viewer));
        given(userFindService.findByUsername(userName)).willReturn(of(userToView));
        given(viewer.viewProfile(userToView)).willReturn(profile);

        profileService.viewProfile(1L, userName);

        then(viewer).should(times(1)).viewProfile(userToView);
    }

    @Test
    void when_viewProfile_with_not_exists_username_expect_NoSuchElementException(@Mock UserName userName) {
        when(userFindService.findByUsername(userName)).thenReturn(empty());

        assertThatThrownBy(() ->
                profileService.viewProfile(userName)
        ).isInstanceOf(NoSuchElementException.class);
    }


    @Test
    void when_viewProfile_expect_user_getProfile(@Mock UserName userName, @Mock User user, @Mock Profile profile) {
        given(userFindService.findByUsername(userName)).willReturn(of(user));
        given(user.getProfile()).willReturn(profile);

        profileService.viewProfile(userName);

        then(user).should(times(1)).getProfile();
    }
}
