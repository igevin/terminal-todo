package com.igevin.terminaltodo.core.user.event;

import com.igevin.terminaltodo.core.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserSwitchEvent {
    private User user;
}
