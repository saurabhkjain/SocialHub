package com.fdm.socialhub.handler;

import com.fdm.socialhub.entity.User;
import com.fdm.socialhub.repositories.UserRepository;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

/**
 * Created by saurabh.jain on 22/09/2015.
 */
@Component
public class UserHandler {
    @Inject
    private UserRepository userRepository;

    public User findUser() {
        return userRepository.findOne(1L);
    }
}

