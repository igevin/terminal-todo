package com.igevin.terminaltodo.core.todo.persistence.impl;

import com.igevin.terminaltodo.core.TodoTask;
import com.igevin.terminaltodo.core.todo.persistence.UserTodoTaskService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RedisUserTodoTaskService implements UserTodoTaskService {
    @Override
    public TodoTask createTodoTask(TodoTask todoTask, long todoListId) {
        return null;
    }

    @Override
    public TodoTask updateTodoTask(TodoTask todoTask) {
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
