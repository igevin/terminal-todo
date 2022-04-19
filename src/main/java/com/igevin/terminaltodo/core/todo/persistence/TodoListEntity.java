package com.igevin.terminaltodo.core.todo.persistence;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TodoListEntity {
    private Long id;
    private String username;
    private LocalDateTime createTime;
}
