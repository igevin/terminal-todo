package com.igevin.terminaltodo.core.todo.persistence;

import com.igevin.terminaltodo.core.TodoList;
import com.igevin.terminaltodo.core.TodoTask;
import com.igevin.terminaltodo.core.user.User;

import java.util.List;
import java.util.Optional;

public interface UserTodoListService {
    TodoList createTodoList(TodoList todoList);
    TodoList getTodoList(long TodoListId);
    TodoList getDefaultTodoList(String username);
    TodoList getDefaultTodoList(User user);

    TodoTask addTask(String content, long todoListId);

    Optional<TodoTask> getTaskById(long taskId);

    void removeTask(long taskId);


    List<TodoTask> listAllTasks(long todoListId);
    List<TodoTask> listUncheckedTasks(long todoListId);
    List<TodoTask> listCheckedTasks(long todoListId);
}
