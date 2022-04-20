package com.igevin.terminaltodo.core.user.state;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
public class AnonymousUser {
    @Getter
    private final String username = "anonymous";
    @Getter
    private final String password = "anonymous";
}
