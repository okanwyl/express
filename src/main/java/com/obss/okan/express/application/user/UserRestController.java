package com.obss.okan.express.application.user;

import com.obss.okan.express.domain.jwt.JWTSerializer;
import com.obss.okan.express.domain.user.Email;
import com.obss.okan.express.domain.user.UserService;
import com.obss.okan.express.infrastructure.jwt.UserJWTPayload;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.obss.okan.express.application.user.UserModel.fromUserAndToken;
import static org.springframework.http.ResponseEntity.of;

@RestController
class UserRestController {
    private final UserService userService;
    private final JWTSerializer jwtSerializer;

    UserRestController(UserService userService, JWTSerializer jwtSerializer) {
        this.userService = userService;
        this.jwtSerializer = jwtSerializer;
    }

    private static String getCurrentCredential() {
        return SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
    }

    @PostMapping("/users/login")
    public ResponseEntity<UserModel> loginUser(@Valid @RequestBody UserLoginRequestDTO dto) {
        return of(userService.login(new Email(dto.getEmail()), dto.getPassword())
                .map(user -> fromUserAndToken(user, jwtSerializer.jwtFromUser(user))));

    }

    @GetMapping("/user")
    public ResponseEntity<UserModel> getUser(@AuthenticationPrincipal UserJWTPayload jwtPayload) {
        return of(userService.findById(jwtPayload.getUserId())
                .map(user -> UserModel.fromUserAndToken(user, getCurrentCredential())));
    }

    @PutMapping("/user")
    public UserModel putUser(@AuthenticationPrincipal UserJWTPayload jwtPayload,
                             @Valid @RequestBody UserPutRequestDTO dto) {
        final var userUpdated =
                userService.updateUser(jwtPayload.getUserId(), dto.toUpdateRequest());
        return fromUserAndToken(userUpdated, getCurrentCredential());
    }
}
