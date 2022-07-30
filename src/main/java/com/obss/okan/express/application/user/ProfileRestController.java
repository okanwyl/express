package com.obss.okan.express.application.user;

import com.obss.okan.express.domain.jwt.JWTPayload;
import com.obss.okan.express.domain.user.Email;
import com.obss.okan.express.domain.user.ProfileService;
import com.obss.okan.express.infrastructure.jwt.UserJWTPayload;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

import static com.obss.okan.express.application.user.ProfileModel.fromProfile;
import static java.util.Optional.ofNullable;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RequestMapping("/profiles")
@RestController
public class ProfileRestController {

    private final ProfileService profileService;

    ProfileRestController(ProfileService profileService) {
        this.profileService = profileService;
    }

    // @TODO
    @GetMapping("/{email}")
    public ProfileModel getProfileByEmail(@AuthenticationPrincipal UserJWTPayload jwtPayload,
                                          @PathVariable Email email) {
        return ofNullable(jwtPayload)
                .map(JWTPayload::getUserId)
                .map(viewerId -> profileService.viewProfile(email))
                .map(ProfileModel::fromProfile)
                .orElseGet(() -> fromProfile(profileService.viewProfile(email)));
    }


    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    void handleNoSuchElementException(NoSuchElementException exception) {
        // return NOT FOUND status
    }


}
