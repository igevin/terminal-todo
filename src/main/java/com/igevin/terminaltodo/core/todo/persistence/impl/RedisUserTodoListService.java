package com.igevin.terminaltodo.core.todo.persistence.impl;

import com.igevin.terminaltodo.core.TodoList;
import com.igevin.terminaltodo.core.TodoTask;
import com.igevin.terminaltodo.core.todo.persistence.UserTodoListService;
import com.igevin.terminaltodo.core.user.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RedisUserTodoListService implements UserTodoListService {

    @Override
    public TodoList createTodoList(TodoList todoList) {
        return null;
    }

    @Override
    public TodoList getTodoList(long TodoListId) {
        return null;
    }

    @Override
    public TodoList getDefaultTodoList(String username) {
        return null;
    }

    @Override
    public TodoList getDefaultTodoList(User user) {
        return null;
    }

    @Override
    public TodoTask addTask(String content, long todoListId) {
        return null;
    }

    @Override
    public Optional<TodoTask> getTaskById(long taskId) {
        return Optional.empty();
    }

    @Override
    public void removeTask(long taskId) {

    }

    @Override
    public List<TodoTask> listAllTasks(long todoListId) {
        return null;
    }

    @Override
    public List<TodoTask> listUncheckedTasks(long todoListId) {
        return null;
    }

    @Override
    public List<TodoTask> listCheckedTasks(long todoListId) {
        return null;
    }
}
