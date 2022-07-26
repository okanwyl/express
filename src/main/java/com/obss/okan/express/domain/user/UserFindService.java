package com.obss.okan.express.domain.user;

import java.util.Optional;

public interface UserFindService {
    Optional<User> findById(long id);

    Optional<User> findByUsername(UserName userName);

    Optional<User> findByEmailUser(Email email);

}
