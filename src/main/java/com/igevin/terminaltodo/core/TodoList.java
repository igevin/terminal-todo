package com.igevin.terminaltodo.core;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Getter
public class TodoList {
    private final List<TodoTask> tasks;
    private final LocalDateTime createTime = LocalDateTime.now();

    public TodoList() {
        tasks = new LinkedList<>();
    }

    public void addTask(String content) {
        tasks.add(new TodoTask(content));
    }

    public void removeTask(long taskId) {
        TodoTask task = tasks.stream().parallel()
                .filter(x -> x.getId().equals(taskId))
                .findAny().orElse(null);
        tasks.remove(task);
    }
}
