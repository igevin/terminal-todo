package com.igevin.terminaltodo.core.user.persistence.impl;

import com.igevin.terminaltodo.core.user.User;
import com.igevin.terminaltodo.core.user.persistence.UserStorageService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RedisUserStorageService implements UserStorageService {
    @Override
    public User saveUser(String username, String password) {
        return null;
    }

    @Override
    public User getUser(String username) {
        return null;
    }

    @Override
    public List<User> listUser() {
        return null;
    }
}
