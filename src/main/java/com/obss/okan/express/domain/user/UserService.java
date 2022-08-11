package com.obss.okan.express.domain.user;

import com.obss.okan.express.domain.exception.EmailAlreadyInUseException;
import com.obss.okan.express.domain.exception.UserNameAlreadyInUseException;
import com.obss.okan.express.domain.exception.UserNotFoundException;
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

    @Transactional
    public User createUser(CreateUserRequest request) {
        final var encodedPassword = Password.of(request.getRawPassword(), passwordEncoder);
        if (userRepository.findByProfileEmail(request.getEmail()).isPresent())
            throw new EmailAlreadyInUseException("Email is already used!");
        if (userRepository.findByProfileUserName(request.getUserName()).isPresent())
            throw new UserNameAlreadyInUseException("Username is already used!");
        return userRepository
                .save(User.of(request.getEmail(), request.getUserName(), encodedPassword, request.getUserType()));
    }

    @Transactional(readOnly = true)
    public Optional<User> login(Email email, String rawPassword) {
        if (!userRepository.findByProfileEmail(email).isPresent()) {
            throw new UserNotFoundException("Email is not used in our database?");
        }
        return userRepository.findByProfileEmail(email)
                .filter(user -> user.matchesPassword(rawPassword, passwordEncoder));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findByEmail(Email email) {
        return userRepository.findByProfileEmail(email);
    }


    @Transactional
    public User updateUser(long id, UserUpdateRequest request) {
        final var user = userRepository.findById(id).orElseThrow(NoSuchElementException::new);
        request.getNameToUpdate().ifPresent(user::changeName);
        request.getSurnameToUpdate().ifPresent(user::changeSurname);
        request.getPasswordToUpdate().map(rawPassword -> Password.of(rawPassword, passwordEncoder))
                .ifPresent(user::changePassword);
        request.getBioToUpdate().ifPresent(user::changeBio);
        return userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findByUserName(UserName userName) {
        return userRepository.findByProfileUserName(userName);
    }
}
