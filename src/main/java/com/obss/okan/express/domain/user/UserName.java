package com.obss.okan.express.domain.user;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class UserName {
    @Column(name = "username", nullable = false)
    private String userName;

    public UserName(String userName){
        this.userName = userName;;;;
    }

    protected UserName(){

    }

    @Override
    public String toString(){
        return userName;
    }
        @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final var userName = (UserName) o;
        return userName.equals(userName.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName);
    }
}
