package com.igevin.terminaltodo.core.user.service.impl;

import com.igevin.terminaltodo.core.user.User;
import com.igevin.terminaltodo.core.user.Users;
import com.igevin.terminaltodo.core.user.service.UserService;
import com.igevin.terminaltodo.core.user.state.CurrentUser;
import com.igevin.terminaltodo.core.user.state.LoggedInUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private Users users;
    @Autowired
    private LoggedInUsers loggedInUsers;
    @Autowired
    private CurrentUser currentUser;

    @Override
    public User createUser(String username, String password) {
        User user = new User(username, password);
        users.getUsers().add(user);
        return user;
    }

    @Override
    public User getUser(String username) {
        return users.getUsers().stream().parallel()
                .filter(user -> user.getUsername().equals(username))
                .findAny().orElse(null);
    }

    @Override
    public Optional<User> getCurrentUser() {
        return currentUser.getUser();
    }

    @Override
    public List<User> listUsers() {
        return users.getUsers();
    }

    @Override
    public boolean validateUser(User user, String password) {
        return user.validateId(password);
    }

    @Override
    public boolean switchUser(String username) {
        if (!loggedInUsers.getUsers().containsKey(username)) {
            return false;
        }
        User user = loggedInUsers.getUsers().get(username);
        updateCurrentUser(user);
        return true;
    }

    private void updateCurrentUser(User user) {
        currentUser.setUser(Optional.of(user));
    }

    @Override
    public boolean login(String username, String password) {
        User user = this.getUser(username);
        if (this.validateUser(user, password)) {
            loggedInUsers.getUsers().put(username, user);
            updateCurrentUser(user);
            return true;
        }
        return false;
    }

    @Override
    public void logout(String username) {
        loggedInUsers.getUsers().remove(username);
    }

    @Override
    public void logout(User user) {
        this.logout(user.getUsername());
    }
}
