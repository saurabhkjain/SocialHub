package com.fdm.socialhub.controller;

import com.fdm.socialhub.entity.Message;
import com.fdm.socialhub.entity.User;
import com.fdm.socialhub.handler.UserHandler;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

/**
 * Created by saurabh.jain on 22/09/2015.
 */
@RestController
public class TestController {
    @Inject
    private UserHandler userHandler;

    @RequestMapping(value = "/hello/{player}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Message message(@PathVariable String player) {

        return new Message(player, "Hello " + player);
    }


    @RequestMapping(value = "/user", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public User getUser() {
        return userHandler.findUser();
    }


}
