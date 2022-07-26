package com.obss.okan.express.domain.user;

import org.springframework.stereotype.Service;

//import javax.transaction.Transactional;
import java.util.NoSuchElementException;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProfileService {
    private final UserFindService userFindService;

    ProfileService(UserFindService userFindService) {
        this.userFindService = userFindService;
    }
    @Transactional(readOnly = true)
    public Profile viewProfile(long viewerId, UserName usernameToView) {
        final var viewer = userFindService.findById(viewerId).orElseThrow(NoSuchElementException::new);
        return userFindService.findByUsername(usernameToView)
                .map(viewer::viewProfile)
                .orElseThrow(NoSuchElementException::new);
    }
    @Transactional(readOnly = true)
    public Profile viewProfile(UserName userName) {
        return userFindService.findByUsername(userName)
                .map(User::getProfile)
                .orElseThrow(NoSuchElementException::new);
    }

}
