package com.obss.okan.express.domain.user;

import java.util.Optional;

public interface UserFindService {
    Optional<User> findById(long id);

    Optional<User> findByEmail(Email email);
    Optional<User> findByUserName(UserName userName);

}

