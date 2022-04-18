package com.igevin.terminaltodo.core.user.persistence;

import com.igevin.terminaltodo.core.user.User;

import java.util.List;

public interface UserStorageService {
    User saveUser(String username, String password);
    User getUser(String username);
    List<User> listUser();
}
