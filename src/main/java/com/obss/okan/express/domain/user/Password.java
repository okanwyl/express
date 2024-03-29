package com.obss.okan.express.domain.user;

import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
class Password {
    @Column(name = "password", nullable = false)
    private String encodedPassword;

    private Password(String encodedPassword) {
        this.encodedPassword = encodedPassword;
    }

    protected Password() {}

    static Password of(String rawPassword, PasswordEncoder passwordEncoder) {
        return new Password(passwordEncoder.encode(rawPassword));
    }

    boolean matchesPassword(String rawPassword, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
