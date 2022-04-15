package com.igevin.terminaltodo.core.user.service;

import com.igevin.terminaltodo.core.user.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User createUser(String username, String password);
    User getUser(String username);
    Optional<User> getCurrentUser();
    List<User> listUsers();
    boolean validateUser(User user, String password);
    boolean switchUser(String username);
    boolean login(String username, String password);
    void logout(String username);
    void logout(User user);

}
