package com.obss.okan.express.domain.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class UserRepositoryTest {
    private static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();


    @Autowired
    private UserRepository userRepository;
    @Test
    void when_save_user_expect_saved() {
        var userToSave = User.of(new Email("user@email.com"),
                new UserName("name"),
                Password.of("rawPassword", PASSWORD_ENCODER),
                UserType.DEVELOPER);

        assertThat(userRepository.save(userToSave)).hasNoNullFieldsOrProperties();
    }

    @Test
    void when_save_user_with_image_expect_saved() {
        var userToSave = User.of(new Email("user@email.com"),
                new UserName("name"),
                Password.of("rawPassword", PASSWORD_ENCODER),
                UserType.DEVELOPER);
        var imageToSave = new Image("some-image");

        userToSave.changeImage(imageToSave);

        assertThat(userRepository.save(userToSave))
                .extracting(User::getImage)
                .isEqualTo(imageToSave);
    }

}
