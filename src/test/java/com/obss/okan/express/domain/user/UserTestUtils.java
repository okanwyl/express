package com.obss.okan.express.domain.user;

import org.springframework.test.util.ReflectionTestUtils;


public class UserTestUtils {
    public static User databaseUser() {
        final var password = new Password();
        ReflectionTestUtils.setField(password, "encodedPassword", "$2y$10$Uw0vceuCbx3bVOsXZuP");
        final var databaseUser = User.of(
                new Email("databaseUser@email.com"),
                "name",
                "surname",
                password,
                UserType.DEVELOPER);
        ReflectionTestUtils.setField(databaseUser, "id", 1L);
        return databaseUser;
    }

    public static User databaseElevatedUser() {
        final var password = new Password();
        ReflectionTestUtils.setField(password, "encodedPassword", "$2y$10$Uw0vceuCbx3bVOsXZuP");
        final var databaseElevatedUser = User.of(
                new Email("elevated@email.com"),
                "elevatedName",
                "elevatedSurname",
                password,
                UserType.SYSADMIN);
        ReflectionTestUtils.setField(databaseElevatedUser, "id", 1L);
        return databaseElevatedUser;
    }
}
