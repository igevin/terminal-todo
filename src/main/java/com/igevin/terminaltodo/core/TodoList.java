package com.igevin.terminaltodo.core;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

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


    public List<TodoTask> listAllTasks() {
        return tasks;
    }

    public List<TodoTask> listUncheckedTasks() {
        return tasks.stream().filter(x -> !x.isChecked()).collect(Collectors.toList());
    }

    public List<TodoTask> listCheckedTasks() {
        return tasks.stream().filter(TodoTask::isChecked).collect(Collectors.toList());
    }


}
