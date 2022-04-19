package com.igevin.terminaltodo.core.user.persistence.impl.mysql;

import com.igevin.terminaltodo.core.user.User;
import com.igevin.terminaltodo.core.user.persistence.UserEntity;
import com.igevin.terminaltodo.core.user.persistence.UserStorageService;
import com.igevin.terminaltodo.core.user.persistence.mock.UserStorageMock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MysqlUserStorageService implements UserStorageService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User saveUser(String username, String password) {
        User user = new User(username, password);
        Integer result = userMapper.saveUser(user.toEntity());
        return result.equals(1) ? user : null;
    }

    @Override
    public User getUser(String username) {
        UserEntity userEntity = userMapper.getUser(username);
        return userEntity == null ? null : new User(userEntity);
    }

    @Override
    public List<User> listUser() {
        return userMapper.listUser().stream().map(User::new).collect(Collectors.toList());
    }
}
