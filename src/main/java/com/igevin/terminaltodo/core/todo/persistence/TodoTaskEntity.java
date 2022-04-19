package com.igevin.terminaltodo.core.todo.persistence;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TodoTaskEntity {
    private Long id;
    private String content;
    private boolean checked;
    private Long listId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
