package com.igevin.terminaltodo.core.user.state;

import com.igevin.terminaltodo.core.user.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CurrentUser {
    @Getter
    @Setter
    private Optional<User> user = Optional.empty();
}
