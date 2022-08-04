package com.obss.okan.express.domain.user;

import javax.persistence.Embeddable;
import java.util.Arrays;
import java.util.NoSuchElementException;

public enum UserType {
    SYSADMIN(0), PROJECT_MANAGER(1), TEAM_LEADER(2), DEVELOPER(3);

    private int type;

    UserType(int type) {}


    public static UserType lookup(Integer type) {
        return Arrays.stream(UserType.values()).filter(value -> value.getType().equals(type))
                .findAny().orElseThrow(NoSuchElementException::new);
    }

    public Integer getType() {
        return type;
    }
}
