package com.obss.okan.express.infrastructure.jwt;

import com.obss.okan.express.domain.jwt.JWTPayload;
import com.obss.okan.express.domain.user.User;

import static java.lang.String.format;
import static java.lang.String.valueOf;

public class UserJWTPayload implements JWTPayload {
    private final long sub;
    private final String name;
    private final long iat;

    UserJWTPayload(long sub, String name, long iat) {
        this.sub = sub;
        this.name = name;
        this.iat = iat;
    }

    static UserJWTPayload of(User user, long epochSecondExpired) {
        return new UserJWTPayload(user.getId(), valueOf(user.getEmail()), epochSecondExpired);
    }

    @Override
    public long getUserId() {
        return 0;
    }

    @Override
    public boolean isExpired() {
        return false;
    }

    @Override
    public String toString() {
        return format("{\"sub\":%d,\"name\":\"%s\",\"iat\":%d}", sub, name, iat);
    }

}
