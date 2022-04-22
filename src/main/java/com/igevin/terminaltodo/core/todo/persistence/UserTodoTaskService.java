package com.igevin.terminaltodo.core.todo.persistence;

import com.igevin.terminaltodo.core.todo.TodoTask;

import java.util.List;
import java.util.Optional;

public interface UserTodoTaskService {
    TodoTask createTodoTask(TodoTask todoTask);
    TodoTask updateTodoTask(TodoTask todoTask);
    Optional<TodoTask> getTaskById(long taskId);
    void removeTask(long taskId);
    List<TodoTask> listTasks(long todoListId, Boolean checked);

    void clearTasks(long todoListId);
    void addTasks(List<TodoTask> tasks, long todoListId);
    void addOrUpdateTasks(List<TodoTask> tasks, long todoListId);
}
