package com.igevin.terminaltodo.core.todo.persistence.impl;

import com.igevin.terminaltodo.core.todo.TodoTask;
import com.igevin.terminaltodo.core.todo.persistence.UserTodoTaskService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RedisUserTodoTaskService implements UserTodoTaskService {
    @Override
    public TodoTask createTodoTask(TodoTask todoTask) {
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
    public List<TodoTask> listTasks(long todoListId, Boolean checked) {
        return null;
    }

}
