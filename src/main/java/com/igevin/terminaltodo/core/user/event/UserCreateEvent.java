package com.igevin.terminaltodo.core.user.event;

import com.igevin.terminaltodo.core.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserCreateEvent {
    private User user;
}
