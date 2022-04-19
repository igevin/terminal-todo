package com.igevin.terminaltodo.core.user.service.impl;

import com.google.common.eventbus.EventBus;
import com.igevin.terminaltodo.core.user.User;
import com.igevin.terminaltodo.core.user.event.UserCreateEvent;
import com.igevin.terminaltodo.core.user.event.UserSwitchEvent;
import com.igevin.terminaltodo.core.user.persistence.UserStorageService;
import com.igevin.terminaltodo.core.user.service.UserService;
import com.igevin.terminaltodo.core.user.state.CurrentUser;
import com.igevin.terminaltodo.core.user.state.LoggedInUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@DependsOn(value = {"userStorageService", "eventBus"})
public class UserServiceImpl implements UserService {
    @Autowired
    @Qualifier("userStorageService")
    private UserStorageService userStorageService;
    @Autowired
    private LoggedInUsers loggedInUsers;
    @Autowired
    private CurrentUser currentUser;
    @Autowired
    private EventBus eventBus;

    @Override
    public User createUser(String username, String password) {
        User user = userStorageService.saveUser(username, password);
        eventBus.post(new UserCreateEvent(user));
        return user;
    }

    @Override
    public User getUser(String username) {
        return userStorageService.getUser(username);
    }

    @Override
    public Optional<User> getCurrentUser() {
        return currentUser.getUser();
    }

    @Override
    public List<User> listUsers() {
        return userStorageService.listUser();
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
        currentUser.setUser(Optional.ofNullable(user));
        eventBus.post(new UserSwitchEvent(user));
    }

    @Override
    public boolean login(String username, String password) {
        User user = this.getUser(username);
        if (user != null && this.validateUser(user, password)) {
            loggedInUsers.getUsers().put(username, user);
            updateCurrentUser(user);
            return true;
        }
        return false;
    }

    @Override
    public void logout(String username) {
        loggedInUsers.getUsers().remove(username);
        updateCurrentUser(null);
    }

    @Override
    public void logout(User user) {
        this.logout(user.getUsername());
    }
}
