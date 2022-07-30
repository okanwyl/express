package com.obss.okan.express.domain.jwt;

import com.obss.okan.express.domain.user.User;

public interface JWTSerializer {
    String jwtFromUser(User user);
}
