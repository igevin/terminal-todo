package com.igevin.terminaltodo.core.user.persistence.impl;

import com.igevin.terminaltodo.core.user.User;
import com.igevin.terminaltodo.core.user.persistence.UserStorageService;
import com.igevin.terminaltodo.core.user.persistence.mock.UserStorageMock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MysqlUserStorageService implements UserStorageService {
    @Autowired
    private UserStorageMock userStorageMock;
    @Override
    public User saveUser(String username, String password) {
        return userStorageMock.saveUser(username, password);
    }

    @Override
    public User getUser(String username) {
        return userStorageMock.getUser(username);
    }

    @Override
    public List<User> listUser() {
        return userStorageMock.listUsers();
    }
}
