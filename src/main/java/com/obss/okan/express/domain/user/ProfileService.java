package com.obss.okan.express.domain.user;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
public class ProfileService {
    private final UserFindService userFindService;

    ProfileService(UserFindService userFindService) {
        this.userFindService = userFindService;
    }

    @Transactional(readOnly = true)
    public Profile viewProfile(UserName userName) {
        return userFindService.findByUserName(userName).map(User::getProfile).orElseThrow(NoSuchElementException::new);
    }

    @Transactional(readOnly = true)
    public Profile viewProfile(long userId) {
        return userFindService.findById(userId).map(User::getProfile).orElseThrow(NoSuchElementException::new);
    }
}
