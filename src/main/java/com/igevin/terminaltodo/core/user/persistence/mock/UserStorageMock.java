package com.igevin.terminaltodo.core.user.persistence.mock;

import com.igevin.terminaltodo.core.user.User;
import com.igevin.terminaltodo.core.user.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserStorageMock {
    @Autowired
    private Users users;
    public User saveUser(String username, String password) {
        User user = new User(username, password);
        users.getUsers().add(user);
        return user;
    }

    public User getUser(String username) {
        return users.getUsers().stream().parallel()
                .filter(user -> user.getUsername().equals(username))
                .findAny().orElse(null);
    }

    public List<User> listUsers() {
        return users.getUsers();
    }

}
