package com.obss.okan.express.domain.user;

import org.springframework.data.repository.Repository;

import java.util.Optional;

interface UserRepository extends Repository<User, Long> {
    User save(User user);

    Optional<User> findById(long id);

    Optional<User> findByProfileEmail(Email email);

    Optional<User> findByProfileUserName(UserName userName);
}
