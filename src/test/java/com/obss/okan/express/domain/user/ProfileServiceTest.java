package com.obss.okan.express.domain.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;

import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

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
    void when_viewProfile_with_viewer_not_exists_expect_NoSuchElementException(@Mock Email email) {
        when(userFindService.findByEmail(email)).thenReturn(empty());

        assertThatThrownBy(() ->
                profileService.viewProfile(email)
        ).isInstanceOf(NoSuchElementException.class);
    }
    @Test
    void when_viewProfile_expect_user_getProfile(@Mock Email email, @Mock User user, @Mock Profile profile) {
        given(userFindService.findByEmail(email)).willReturn(of(user));
        given(user.getProfile()).willReturn(profile);

        profileService.viewProfile(email);

        then(user).should(times(1)).getProfile();
    }
}
