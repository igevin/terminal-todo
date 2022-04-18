package com.igevin.terminaltodo.core.user;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Deprecated
public class Users {
    @Getter
    private final List<User> users = new ArrayList<>();
}
