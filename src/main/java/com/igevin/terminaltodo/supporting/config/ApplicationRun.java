package com.igevin.terminaltodo.supporting.config;

import com.igevin.terminaltodo.core.user.User;
import com.igevin.terminaltodo.core.user.service.UserService;
import com.igevin.terminaltodo.core.user.state.AnonymousUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class ApplicationRun implements ApplicationRunner {
    @Autowired
    private UserService userService;
    @Autowired
    private AnonymousUser anonymous;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        User anonymousUser = userService.getUser(anonymous.getUsername());
        if (anonymousUser == null) {
            userService.createUser(anonymous.getUsername(), anonymous.getPassword());
        }
        userService.login(anonymous.getUsername(), anonymous.getPassword());
    }
}
