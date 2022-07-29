package com.obss.okan.express.domain.user;

import java.util.Optional;

public interface UserFindService {
  Optional<User> findById(long id);
  Optional<User> findByEmail(Email email);
  // @TODO should we add the name and surname services?
  // Optional<User> findByName(Email email);
  // Optional<User> findBySurname(Email email);
}
