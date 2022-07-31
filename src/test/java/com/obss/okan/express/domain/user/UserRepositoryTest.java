package com.obss.okan.express.domain.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {
    private static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();
    @Autowired
    private UserRepository userRepository;

    @Test
    void when_save_user_expect_saved() {
        var userToSave = User.of(new Email("user@email.com"),
                "name",
                "surname",
                Password.of("rawPassword", PASSWORD_ENCODER),
                UserType.PROJECT_MANAGER);

        assertThat(userRepository.save(userToSave)).hasNoNullFieldsOrProperties();
    }

    @Test
    void when_save_user_with_image_expect_saved() {
        var userToSave = User.of(new Email("user@email.com"),
                "name",
                "surname",
                Password.of("rawPassword", PASSWORD_ENCODER),
                UserType.PROJECT_MANAGER);


        var imageToSave = new Image("some-image");

        userToSave.changeImage(imageToSave);

        assertThat(userRepository.save(userToSave))
                .extracting(User::getImage)
                .isEqualTo(imageToSave);
    }

}
