package com.igevin.terminaltodo.core.todo.persistence;

import com.igevin.terminaltodo.core.TodoTask;

import java.util.List;
import java.util.Optional;

public interface UserTodoTaskService {
    TodoTask createTodoTask(TodoTask todoTask, long todoListId);
    TodoTask updateTodoTask(TodoTask todoTask);
    Optional<TodoTask> getTaskById(long taskId);
    void removeTask(long taskId);
    List<TodoTask> listAllTasks(long todoListId);
    List<TodoTask> listUncheckedTasks(long todoListId);
    List<TodoTask> listCheckedTasks(long todoListId);
}
