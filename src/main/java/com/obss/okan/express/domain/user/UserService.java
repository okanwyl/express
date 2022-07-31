package com.obss.okan.express.domain.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService implements UserFindService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public Optional<User> login(Email email, String rawPassword) {
        return userRepository.findByEmail(email)
                .filter(user -> user.matchesPassword(rawPassword, passwordEncoder));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findByEmail(Email email) {
        return userRepository.findByEmail(email);
    }

    @Transactional
    public User createUser(CreateUserRequest request) {
        final var encodedPassword = Password.of(request.getRawPassword(), passwordEncoder);
        return userRepository.save(User.of(request.getEmail(), request.getName(), request.getSurname(), encodedPassword, null));
    }

    @Transactional
    public User updateUser(long id, UserUpdateRequest request) {
        final var user = userRepository.findById(id).orElseThrow(NoSuchElementException::new);
        request.getEmailToUpdate().ifPresent(user::changeEmail);
        request.getNameToUpdate().ifPresent(user::changeName);
        request.getSurnameToUpdate().ifPresent(user::changeSurname);
        request.getPasswordToUpdate().map(rawPassword -> Password.of(rawPassword, passwordEncoder))
                .ifPresent(user::changePassword);
        request.getImageToUpdate().ifPresent(user::changeImage);
        request.getBioToUpdate().ifPresent(user::changeBio);
        return userRepository.save(user);
    }
}
