package com.igevin.terminaltodo.core.todo.persistence.impl.mysql;

import com.igevin.terminaltodo.core.TodoList;
import com.igevin.terminaltodo.core.TodoTask;
import com.igevin.terminaltodo.core.todo.persistence.TodoListEntity;
import com.igevin.terminaltodo.core.todo.persistence.UserTodoListService;
import com.igevin.terminaltodo.core.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MysqlUserTodoListService implements UserTodoListService {
    @Autowired
    private TodoListMapper todoListMapper;
    @Autowired
    private MysqlUserTodoTaskService todoTaskService;

    @Override
    public TodoList createTodoList(TodoList todoList) {
        todoListMapper.createTodoList(todoList.toEntity());
        return todoList;
    }

    @Override
    public TodoList getTodoList(long TodoListId) {
        return null;
    }

    @Override
    public TodoList getDefaultTodoList(String username) {
        TodoListEntity entity = todoListMapper.getDefaultUserTodoList(username);
        return new TodoList(entity);
    }

    @Override
    public TodoList getDefaultTodoList(User user) {
        return getDefaultTodoList(user.getUsername());
    }

    @Override
    public TodoTask addTask(String content, long todoListId) {
        TodoTask todoTask = new TodoTask(content, todoListId);
        todoTaskService.createTodoTask(todoTask);
        return todoTask;
    }

    @Override
    public Optional<TodoTask> getTaskById(long taskId) {
        return todoTaskService.getTaskById(taskId);
    }

    @Override
    public void removeTask(long taskId) {
        todoTaskService.removeTask(taskId);
    }

    @Override
    public List<TodoTask> listAllTasks(long todoListId) {
        return todoTaskService.listTasks(todoListId, null);
    }

    @Override
    public List<TodoTask> listUncheckedTasks(long todoListId) {
        return todoTaskService.listTasks(todoListId, false);
    }

    @Override
    public List<TodoTask> listCheckedTasks(long todoListId) {
        return todoTaskService.listTasks(todoListId, true);
    }
}
