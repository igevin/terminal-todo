package com.igevin.terminaltodo.core.user.state;

import com.igevin.terminaltodo.core.user.User;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class LoggedInUsers {
    @Getter
    private final Map<String, User> users = new HashMap<>();
}
