package com.fdm.socialhub.service;

import com.fdm.socialhub.entity.User;
import com.fdm.socialhub.repositories.UserRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * Created by saurabh.jain on 29/09/2015.
 */
@Service
public class UserService {
    @Inject
    private UserRepository userRepository;

    public User getUser(long id){
        return userRepository.findOne(id);
    }
}
