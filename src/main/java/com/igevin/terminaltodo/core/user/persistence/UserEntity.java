package com.igevin.terminaltodo.core.user.persistence;

import lombok.Data;

@Data
public class UserEntity {
    private Long id;
    private String username;
    private String password;

}
