package com.igevin.terminaltodo.core.todo.persistence.impl.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.igevin.terminaltodo.core.todo.TodoList;
import com.igevin.terminaltodo.core.todo.TodoTask;
import com.igevin.terminaltodo.core.todo.persistence.UserTodoListService;
import com.igevin.terminaltodo.core.user.User;
import com.igevin.terminaltodo.supporting.serializer.Serializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RedisUserTodoListService implements UserTodoListService {
    @Autowired
    private RedisUserTodoTaskService todoTaskService;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private Serializer serializer;

    private final String prefix = "todo::list::";

    @Override
    public TodoList createTodoList(TodoList todoList) {
        String key = prefix + todoList.getUsername();
        String value = serializer.serialize(todoList);
        redisTemplate.opsForValue().set(key, value);
        return todoList;
    }

    @Override
    public TodoList getTodoList(long TodoListId) {
        return null;
    }

    @Override
    public TodoList getDefaultTodoList(String username) {
        String key = prefix + username;
        String value = redisTemplate.opsForValue().get(key);
        return serializer.deserialize(value, TodoList.class);
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
