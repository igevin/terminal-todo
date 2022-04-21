package com.igevin.terminaltodo.core.user.persistence.impl.redis;

import com.igevin.terminaltodo.core.user.User;
import com.igevin.terminaltodo.core.user.persistence.UserEntity;
import com.igevin.terminaltodo.core.user.persistence.UserStorageService;
import com.igevin.terminaltodo.supporting.serializer.Serializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RedisUserStorageService implements UserStorageService {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private Serializer serializer;
    private final String prefix = "todo::user::";
    @Override
    public User saveUser(String username, String password) {
        User user = new User(username, password);
        redisTemplate.opsForHash().put(prefix, username, serializer.serialize(user.toEntity()));
        return user;
    }

    @Override
    public User getUser(String username) {
        Object user = redisTemplate.opsForHash().get(prefix, username);
        if (user == null) {
            return null;
        }
        UserEntity entity = serializer.deserialize((String) user, UserEntity.class);
        return new User(entity);
    }

    @Override
    public List<User> listUser() {
        return redisTemplate.opsForHash().entries(prefix).values()
                .stream().map(x -> new User(serializer.deserialize((String) x, UserEntity.class)))
                .collect(Collectors.toList());
    }
}
